package br.com.ioasys.camp.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.ioasys.camp.empresas.DetailsFragmentArgs
import br.com.ioasys.camp.empresas.R
import br.com.ioasys.camp.empresas.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DetailsFragment : Fragment () {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
        setupToolbar()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding.toolbarCompanyDetails)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    private fun configureView() {
        binding.tvCompanyDescription.text = args.companyDescription
        binding.tvCompanyName.text = args.companyName
        Glide
            .with(this)
            .load(args.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.ivCompanyImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
