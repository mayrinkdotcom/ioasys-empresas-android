package br.com.ioasys.camp.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.ioasys.camp.empresas.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val adapter by lazy { CompanyAdapter(::clickItem) }

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

        adapter.setItems(
            listOf(
                Company(
                    0,
                    "Nome1",
                    "Descricao1",
                    "imagem1",
                    "Brasil1",
                    CompanyType(
                        0, "valor1"
                    )
                ),
                Company(
                    0,
                    "Nome2",
                    "Descricao2",
                    "imagem2",
                    "Brasil2",
                    CompanyType(
                        0, "valor2"
                    )
                ),
                Company(
                    0,
                    "Nome3",
                    "Descricao3",
                    "imagem3",
                    "Brasil3",
                    CompanyType(
                        0, "valor3"
                    )
                ),
                Company(
                    0,
                    "Nome4",
                    "Descricao4",
                    "imagem4",
                    "Brasil4",
                    CompanyType(
                        0, "valor4"
                    )
                ),
                Company(
                    0,
                    "Nome5",
                    "Descricao5",
                    "imagem",
                    "Brasil5",
                    CompanyType(
                        0, "valor5"
                    )
                ),
                Company(
                    0,
                    "Nome6",
                    "Descricao6",
                    "imagem6",
                    "Brasil6",
                    CompanyType(
                        0, "valor6"
                    )
                ),
                Company(
                    0,
                    "Nome7",
                    "Descricao7",
                    "imagem7",
                    "Brasil7",
                    CompanyType(
                        0, "valor7"
                    )
                ),
                Company(
                    0,
                    "Nome8",
                    "Descricao8",
                    "imagem8",
                    "Brasil8",
                    CompanyType(
                        0, "valor8"
                    )
                ),
                Company(
                    0,
                    "Nome9",
                    "Descricao9",
                    "imagem",
                    "Brasil9",
                    CompanyType(
                        0, "valor9"
                    )
                ),
                Company(
                    0,
                    "Nome10",
                    "Descricao10",
                    "imagem10",
                    "Brasil10",
                    CompanyType(
                        0, "valor10"
                    )
                )
            )
        )
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