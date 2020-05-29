package com.example.iikoapi.startapp.datatype

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Address(
    var street: String /* string Наименование улицы 255 **/,
    var home: String /* string Дом 10 **/,
    var housing: String? /* string Корпус 10*/,
    var apartment: String? /* string Квартира 10*/
){
    constructor(
        city: String /* string Наименование города 255 */,
        street: String /* string Наименование улицы 255 **/,
        streetId: String? /* Guid Идентификатор улицы (если справочник улиц синхронизирован с справочником улиц в RMS)*/,
        streetClassifierId: String? /* string Идентификатор улицы в классификаторе, например, КЛАДР. 255*/,
        home: String /* string Дом 10 **/,
        housing: String? /* string Корпус 10*/,
        apartment: String? /* string Квартира 10*/,
        entrance: String? /* string Подъезд 10*/,
        floor: String? /* string Этаж 10*/,
        doorphone: String? /* string Домофон 10*/,
        comment: String? /* string Дополнительная информация 500*/,
        regionId: String? /* Guid Идентификатор района, к которому относится адрес*/,
        externalCartographyId: String? /* string Идентификатор адреса во внешней картографической системе 255*/,
        index: String? /* string Индекс улицы в адресе, если есть 255*/
    ):this(street, home, housing, apartment)
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class CityWithStreets(
    var city: City /*City Ссылка на город */,
    var streets: List<Street> /*Street[] Коллекция улиц, принадлежащих городу*/
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class City(
    var id: String /*Guid Уникальный идентификатор */,
    var name: String /*string Название */,
    var classifierId: String? /* string Идентификатор города в классификаторе, например, КЛАДР*/,
    var additionalInfo: String? /* string Дополнительная информация о городе в свободном формате*/,
    var externalRevision: Int? /* long? Номер ревизии сущности из РМС*/
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Street(
    var id: String /*Guid Уникальный идентификатор */,
    var name: String /* string Название */,
    var cityId: String? /* Guid Идентификатор города*/,
    var classifierId: String? /* string Идентификатор улицы в классификаторе, например, КЛАДР*/,
    var externalRevision: Int? /* long? Номер ревизии сущности из РМС*/
)