package com.example.nusatrip.data.dummy

import com.example.nusatrip.R
import com.example.nusatrip.domain.model.Place
import com.example.nusatrip.domain.model.ReviewPlace

object DummyData {
    val dummyReviews = listOf(
        ReviewPlace(
            "Johan",
            R.drawable.person1,
            "6 months ago",
            4.3,
            "Tempatnya sangat indah, jika kembali ke jogja saya pasti akan kembali"
        ),
        ReviewPlace(
            "Raihani",
            R.drawable.person2,
            "6 months ago",
            4.5,
            "Cuacananya cangat nyaman UwU, Pantainya..... juga cangat indah (>///<)"),
        ReviewPlace(
            "Raja Pon",
            R.drawable.person3,
            "1 month ago",
            4.0,
            "Tempat yang bagus untuk berlibur."),
        ReviewPlace(
            "Rapli",
            R.drawable.person5,
            "2 days ago",
            1.1,
            "Tempat jelek!11!!11 AKU GASUKAAAAA"),
        ReviewPlace(
            "Bron",
            R.drawable.person4,
            "1 day ago", 5.0,
            "BrrrrrBRRRRR BRRR brrrr")
    )
    val places = listOf(
        Place(
            id = 1,
            name = "Taman Sari",
            location = "Kota Yogyakarta, D.I. Yogyakarta",
            description = "Taman Sari, also known as the Water Castle, is a historical site located in Yogyakarta, Indonesia. Built in the mid-18th century.",
            rating = 4.8,
            category = "History",
            imageRes = R.drawable.carousel2,
            reviews = dummyReviews
        ),
        Place(
            id = 2,
            name = "Prambanan Temple",
            location = "Sleman, D.I. Yogyakarta",
            description = "Prambanan Temple is a majestic 9th-century Hindu temple complex located in Yogyakarta, Indonesia. Dedicated to the Trimurti.",
            rating = 4.9,
            category = "History",
            imageRes = R.drawable.carousel3,
            reviews = dummyReviews
        ),
        Place(
            id = 3,
            name = "Gudeg Yu Djum",
            location = "Sleman, Yogyakarta",
            description = "Legendary Gudeg restaurant in Yogyakarta offering authentic sweet jackfruit stew.",
            rating = 4.8,
            category = "Culinary",
            imageRes = R.drawable.localculinary1,
            reviews = dummyReviews
        ),
        Place(
            id = 4,
            name = "Malioboro Street",
            location = "Kota Yogyakarta, D.I. Yogyakarta",
            description = "The most famous street in Yogyakarta. Packed with shops selling curios, and street food.",
            rating = 4.7,
            category = "City",
            imageRes = R.drawable.malioboro,
            reviews = dummyReviews
        ),
        Place(
            id = 5,
            name = "Batik Winotosastro",
            location = "Kota Yogyakarta, D.I. Yogyakarta",
            description = "batik gacor kang",
            rating = 4.2,
            category = "Souvenir",
            imageRes = R.drawable.batik,
            reviews = dummyReviews
        ),
        Place(
            id = 6,
            name = "Museum Geologi",
            location = "Kota Bandung, Jawa Barat",
            description = "Museum geologi di Bandung",
            rating = 4.6,
            category = "History",
            imageRes = R.drawable.museumgeologi,
            reviews = dummyReviews
        ),
        Place(
            id = 7,
            name = "Bakpia Pathok 25",
            location = "Kota Yogyakarta, D.I. Yogyakarta",
            description = "Bakpia Pathok  enak",
            rating = 4.5,
            category = "Culinary",
            imageRes = R.drawable.localbussiness1,
            reviews = dummyReviews
        ),
    )

    fun getExploreRecommendations(): List<Place> {
        return places.filter { it.category == "History" || it.category == "City" }
    }

    fun getLocalFavorites(): List<Place> {
        return places.filter { it.category == "Culinary" || it.category == "Souvenir" }
    }

    fun getPlaceById(id: Int): Place? {
        return places.find { it.id == id }
    }
}