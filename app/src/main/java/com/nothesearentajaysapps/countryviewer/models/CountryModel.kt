package com.nothesearentajaysapps.countryviewer.models

/**
 * A simplified representation of a Country from a JSON blob like the following.
 *   {
 *      "capital": "Washington, D.C.",
 *      "code": "US",
 *      "currency": {
 *          "code": "USD",
 *          "name": "United States dollar",
 *          "symbol": "$"
 *      },
 *      "flag": "https://restcountries.eu/data/usa.svg",
 *      "language": {
 *          "code": "en",
 *          "iso639_2": "eng",
 *          "name": "English",
 *          "nativeName": "English"
 *      },
 *      "name": "United States of America",
 *      "region": "NA"
 *   },
 *
 *   Note: Only the fields asked for by the assignment are parsed.
 *
 * We ensure we properly compare CountryModels for equality based on:
 * - https://kt.academy/article/ek-equals
 */
data class CountryModel(
    var name: String,
    var region: String,
    var code: String,
    var capital: String
)