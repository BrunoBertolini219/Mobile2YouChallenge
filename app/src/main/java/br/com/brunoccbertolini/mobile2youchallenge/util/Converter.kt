package br.com.brunoccbertolini.mobile2youchallenge.util

import br.com.brunoccbertolini.domain.model.Genres

class Converter {

    companion object {
        fun minutesToHour(minutes: Int):String {
            val hours = minutes / 60
            val min = minutes % 60
            return "$hours hora(s) $min minuto(s)"
        }

        fun rateToVolteAvegare(vote: Float): String =
            "$vote / 10 Média de Votos"

        fun genresListToString(genres: List<Genres>):String {
            var genre = ""
            genres.forEach {
                genre = "${it.name} "
            }
            return genre
        }

    }



}