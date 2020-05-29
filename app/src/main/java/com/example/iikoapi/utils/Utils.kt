package com.example.iikoapi.utils

import com.example.iikoapi.startapp.datatype.Product
import com.example.iikoapi.startapp.networking.*

fun getCategories():List<String>{
    val tmp = menu.products.filter {
        it.isIncludedInMenu
    }.groupBy {
        it.parentGroup
    }
    return (List(tmp.keys.size){
        menu.groups.find {group ->
            group.id==tmp.keys.elementAt(it)}?.name.toString()
    })
}
fun getProdsByCategory():List<List<Product>>{
    val tmp = menu.products.filter {
        it.isIncludedInMenu
    }.groupBy {
        it.parentGroup
    }
    return tmp.values.toList()
}