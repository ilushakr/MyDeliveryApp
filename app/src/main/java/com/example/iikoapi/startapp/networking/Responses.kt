package com.example.iikoapi.startapp.networking

import android.util.Log
import com.example.iikoapi.startapp.datatype.Group
import com.example.iikoapi.startapp.datatype.Modifer
import com.example.iikoapi.startapp.datatype.OrganisationInfo
import com.example.iikoapi.startapp.datatype.Product
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import java.lang.Exception

@JsonIgnoreProperties(ignoreUnknown = true)
data class MenuResponse(
    @SerializedName("groups")
    var groups: List<Group>,
    @SerializedName("products")
    var products: List<Product>,
    @SerializedName("revision")
    var revision: Int,
//    var productCategories: List<ProductCategory>,
    var uploadDate: String
){
    fun getByType(type: String):List<Product>{
        try {
            return products.groupBy { it.type }[type]!!.toList()
        }
        catch (e:Exception){
            Log.e("invalid type","only: dish, modifier, good")
            return emptyList<Product>()
        }
    }
    fun groupAndProducts(type: String):Map<Group?,List<Product>>{
        val tmp = getByType(type)
        val ids:Map<String?,List<Product>>
        if (type=="dish")
            ids= tmp.groupBy { it.parentGroup }
        else ids = tmp.groupBy { it.groupId }
        return ids.mapKeys { groups.find {group -> group.id == it.key} }
    }
    fun singleModifiers(modiferList:List<Modifer>):List<Product>{
        val tmp = getByType("modifier")
        return List<Product>(modiferList.size){
            tmp.find { product -> product.id==modiferList[it].modifierId }!!
        }
    }
    fun GroupModifiers(modiferList:List<Modifer>):List<Product>{
        return groupAndProducts("modifier")[groups.find { it.id==modiferList[0].modifierId}]!!
    }
}

data class OrgsResponse(
    var organisations: List<OrganisationInfo>
)
