import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("places")
    val places: List<Place>,
    @SerializedName("status")
    val status: String // ok
)

data class Place(
    @SerializedName("formatted_address")
    val address: String, // 中国北京市
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String // 北京市
)

data class Location(@SerializedName("lng")
                    val lng: String,
    @SerializedName("lat")
    val lat: String

)

