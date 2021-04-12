package br.com.ioasys.camp.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.ioasys.camp.empresas.LoginFragmentDirections
import br.com.ioasys.camp.empresas.databinding.FragmentLoginBinding
import br.com.ioasys.camp.empresas.presentation.LoginViewModel
import br.com.ioasys.camp.empresas.presentation.ViewState.State.*
import com.google.android.material.textfield.TextInputEditText
import okhttp3.Headers

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var button: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var viewLoading: View
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = binding.btnLogin
        edtEmail = binding.edtEmail
        edtPassword = binding.edtPassword
        viewLoading = binding.viewLoading
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)
        loadingGroup = binding.

        button.setOnClickListener {
            viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.headersLiveData.observe(viewLifecycleOwner, {
            when(it.state) {
                SUCESS -> onResultSuccess(it.data)
                ERROR -> onResultError(it.error)
                LOADING -> onLoading(it.isLoading)

            }
        })
    }

    private fun onLoading(loading: Boolean) {
        if(loading) {

        } else {

        }
    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }

    private fun onResultSuccess(data: Headers?) {
        data?.let { headers ->
            findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                            accessToken = headers["access-token"] ?: "",
                            clientId = headers["client"] ?: "",
                            uid = headers["uid"] ?: ""
                    )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
