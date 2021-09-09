package com.wakuzama.temperworks.data

data class TemperWorksItem(
    val id: String? = null,
    val starts_at: String? = null,
    val ends_at: String? = null,
    val earnings_per_hour: EarningsPerHour? = null,
    val job: Jobs,
) {
    data class EarningsPerHour(
        val currency: String? = null,
        val amount: Double? = null,
    )

    data class Jobs(
        val id: String? = null,
        val links: Links,
        val project: Project,
        val category: Category,
        val report_at_address: ReportAtAddress,
    ) {

        data class Links(
            val hero_380_image: String? = null,
        )

        data class Project(
            val client: Client,
        ) {
            data class Client(
                val name: String? = null,
            )
        }

        data class Category(
            val name_translation: NameTranslation,
        ) {

            data class NameTranslation(
                val en_GB: String? = null,
                val nl_NL: String? = null,
            )
        }

        data class ReportAtAddress(
            val links: Link,
        ) {

            data class Link(
                val get_directions: String? = null,
            )
        }

    }

}