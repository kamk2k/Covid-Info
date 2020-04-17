package com.corellidev.data.model

import com.squareup.moshi.Json

data class SupportedCountryResponseModel(
    @Json(name = "Country")
    var country: String? = null,
    @Json(name = "Slug")
    var slug: String? = null,
    @Json(name = "ISO2")
    var iSO2: String? = null
)