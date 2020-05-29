package com.example.iikoapi.startapp.datatype

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Customer(
    var name: String /* string Имя 60 */,
    var phone: String /* string Телефонный номер. Регулярное выражение, которому должен соответствовать телефон. 40 **/
){
    constructor(
        id: String? /* Guid Идентификатор*/,
        name: String /* string Имя 60 */,
        phone: String /* string Телефонный номер. Регулярное выражение, которому должен соответствовать телефон. 40 **/,
        cultureName: String? /* string Языковая культура пользователя, пример: RU-ru*/,
        favouriteDish: String? /* string Любимое блюдо пользователя*/,
        birthday: String? /* ShortDateTime День рождения*/,
        email: String? /* string email 60*/,
        nick: String? /* string Никнэйм 60*/,
        middleName: String? /* string Отчество 60*/,
        surName: String? /* string Фамилия 60*/,
        shouldReceivePromoActionsInfo: Boolean? /* bool? Получает ли пользователь информацию о промоакциях*/,
        sex: String? /* string Пол: NotSpecified = 0, Male = 1, Female = 2. Для входящих запросов передавать 0,1 или 2.*/,
        imageId: String? /* Guid Идентификатор изображения пользователя*/,
        customProperties: String? /* {“Key”:””,” Value”:””}[]массив key-value значений дополнительных свойств*/,
        publicCustomProperties: String? /* {“Key”:””,” Value”:””}[] массив key-value значений публичных дополнительных свойств*/,
        balance: Int? /* decimal Баланс пользователя*/,
        isBlocked: Boolean? /* bool Заблокирован ли пользователь в организации*/,
        additionalPhones: List<CustomerPhone>? /*CustomerPhone[] Дополнительные телефоны*/,
        addresses: List<Address>? /*CustomerAddress[] Адреса*/,
        cards: List<GuestCardInfo>? /*GuestCardInfo[] Карты*/
    ):this(name,phone)
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class CustomerPhone(
    var phone: String /*string Номер телефон*/
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class GuestCardInfo(
    var Id: String /* string Идентификатор карты */,
    var Track: String /* string Трек карты */,
    var Number: String? /* string Номер карты*/,
    var IsActivated: Boolean /* bool Признак, активирована карта или нет */,
    var OrganizationId: String /* Guid Идентификатор организации, в которой действует карта*/
)