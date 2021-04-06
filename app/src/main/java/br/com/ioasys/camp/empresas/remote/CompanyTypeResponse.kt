package br.com.ioasys.camp.empresas.remote

import com.google.gson.annotations.SerializedName

data class CompanyTypeResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("enterprise_type_name")
    val enterpriseTypeName: String
)
