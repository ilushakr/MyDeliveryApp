package com.example.iikoapi.startapp.networking

import com.example.iikoapi.startapp.datatype.Group
import com.example.iikoapi.startapp.datatype.OrganisationInfo
import com.example.iikoapi.startapp.datatype.Product
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class MenuResponse(
    @SerializedName("groups")
    var groups: List<Group>,
    @SerializedName("products")
    var products: List<Product>,
    @SerializedName("revision")
    var revision: Int
)

data class OrgsResponse(
    var organisations: List<OrganisationInfo>
)
