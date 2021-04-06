package br.com.ioasys.camp.empresas.remote

import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (
    @SerializedName("enterprises")
    val companies: List<CompanyResponse>,
)
