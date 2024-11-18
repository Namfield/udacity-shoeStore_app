package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ShoeType : Parcelable {
    RUNNING,
    SOCCER,
    SNEAKERS,
    LOAFERS,
    BOOTS
}

@Parcelize
data class Shoe(
    var name: String,
    var size: String,
    var company: String,
    var description: String,
    var shoeType: ShoeType,
    val shoeTypeImages: MutableList<String> = mutableListOf()
) : Parcelable

//import android.os.Parcelable
//import androidx.databinding.BaseObservable
//import androidx.databinding.Bindable
//import com.udacity.shoestore.models.ShoeType
//
//enum class ShoeType : Parcelable {
//    RUNNING,
//    SOCCER,
//    SNEAKERS,
//    LOAFERS,
//    BOOTS
//}
//
//class Shoe(
//    private var _name: String,
//    private var _size: Double,
//    private var _company: String,
//    private var _description: String,
//    private var _shoeType: ShoeType
//) : BaseObservable() {
//
//    @get:Bindable
//    var name: String
//        get() = _name
//        set(value) {
//            _name = value
//            notifyPropertyChanged(BR.name) // BR is generated by data binding
//        }
//    `1
//    var size: Double
//        @Bindable get() = _size
//        set(value) {
//            _size = value
//            notifyPropertyChanged(BR.size) // Notify that the property has changed
//
//    @get:Bindable
//    var company: String
//        get() = _company
//        set(value) {
//            _company = value
//            notifyPropertyChanged(BR.company)
//        }
//
//    @get:Bindable
//    var description: String
//        get() = _description
//        set(value) {
//            _description = value
//            notifyPropertyChanged(BR.description)
//        }
//
//    @get:Bindable
//    var shoeType: ShoeType
//        get() = _shoeType
//        set(value) {
//            _shoeType = value
//            notifyPropertyChanged(BR.shoeType)
//        }
//}