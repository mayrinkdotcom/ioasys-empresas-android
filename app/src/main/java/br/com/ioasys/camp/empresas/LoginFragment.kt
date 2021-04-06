package br.com.ioasys.camp.empresas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.ioasys.camp.empresas.databinding.FragmentLoginBinding
import br.com.ioasys.camp.empresas.remote.CompanyService
import br.com.ioasys.camp.empresas.remote.LoginRequest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var button: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = binding.btnLogin
        edtEmail = binding.edtEmail
        edtPassword = binding.edtPassword

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = CompanyService.newInstance().login(LoginRequest(
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString()
                ))
                handleLogin(response)
            }
        }
    }

    private fun handleLogin(response: Response<Unit>) {
        if(response.isSuccessful) {
            Log.d("LOGIN", "header access-token ${response.headers().get("access-token")}")
            Log.d("LOGIN", "header client ${response.headers().get("client")}")
            Log.d("LOGIN", "header uid ${response.headers().get("uid")}")

            findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                    accessToken = response.headers().get("access-token") ?: "",
                    clientId = response.headers().get("client") ?: "",
                    uid = response.headers().get("uid") ?: ""
                    )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
