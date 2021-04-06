package br.com.ioasys.camp.empresas.remote

import br.com.ioasys.camp.empresas.Company
import br.com.ioasys.camp.empresas.CompanyType

fun CompanyResponse.toModel(): Company {
    return Company(
            id = id,
            companyName = enterpriseName,
            pathImage = "https://empresas.ioasys.com.br/api/v1/${photo}",
            description = description,
            country = country ?: "",
            companyType = enterpriseType.toModel()
    )
}

fun CompanyTypeResponse.toModel(): CompanyType {
    return CompanyType(
            id = id,
            companyTypeName = enterpriseTypeName
    )
}