package br.com.ioasys.camp.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.ioasys.camp.empresas.databinding.FragmentHomeBinding
import br.com.ioasys.camp.empresas.remote.CompanyService
import br.com.ioasys.camp.empresas.remote.GetCompaniesResponse
import br.com.ioasys.camp.empresas.remote.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val adapter by lazy { CompanyAdapter(::clickItem) }

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        binding.recycler.adapter = adapter
        
        getCompanies()
    }

    private fun getCompanies() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = CompanyService.newInstance().getEnterprises(
                    accessToken = args.accessToken,
                    client = args.clientId,
                    uid = args.uid
            )
            
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Response<GetCompaniesResponse>) {
        if(response.isSuccessful) {
            adapter.setItems(response.body()?.companies?.map { it.toModel() } ?: listOf())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickItem(company: Company) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                companyName = company.companyName,
                imageUrl = company.pathImage,
                companyDescription = company.description
            )
        )
    }

    private fun setupToolbar() {
        val btnSearch: ImageButton = binding.btnSearch
        val logoHomeImageView = binding.ivLogoHome
        btnSearch.setOnClickListener {
            logoHomeImageView.visibility = View.GONE
            val layoutParams = btnSearch.layoutParams
            layoutParams.width = ActionBar.LayoutParams.MATCH_PARENT
        }
    }
}