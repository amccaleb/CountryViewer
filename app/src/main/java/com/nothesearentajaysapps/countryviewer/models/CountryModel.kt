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
 */
class CountryModel {

    lateinit var name: String
    lateinit var region: String
    lateinit var code: String
    lateinit var capital: String
}