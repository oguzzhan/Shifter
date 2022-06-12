package com.ozzy.shifter.model


import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("appearances")
    val appearances: List<Appearance?>? = null,
    @SerializedName("archived_at")
    val archivedAt: Any? = null,
    @SerializedName("category")
    val category: Category? = null,
    @SerializedName("dress_code")
    val dressCode: String? = null,
    @SerializedName("extra_briefing")
    val extraBriefing: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("is_agency")
    val isAgency: Boolean? = null,
    @SerializedName("links")
    val links: Links? = null,
    @SerializedName("project")
    val project: Project? = null,
    @SerializedName("reference")
    val reference: String? = null,
    @SerializedName("report_at_address")
    val reportAtAddress: ReportAtAddress? = null,
    @SerializedName("report_to")
    val reportTo: ReportTo? = null,
    @SerializedName("skills")
    val skills: List<Skill?>? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("tips")
    val tips: Boolean? = null,
    @SerializedName("title")
    val title: String? = null
) {
    data class Appearance(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("name")
        val name: String? = null
    )

    data class Category(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("internalId")
        val internalId: Int? = null,
        @SerializedName("links")
        val links: Links? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("name_translation")
        val nameTranslation: NameTranslation? = null,
        @SerializedName("slug")
        val slug: String? = null
    ) {
        data class Links(
            @SerializedName("icon")
            val icon: String? = null
        )

        data class NameTranslation(
            @SerializedName("en_GB")
            val enGB: String? = null,
            @SerializedName("nl_NL")
            val nlNL: String? = null
        )
    }

    data class Links(
        @SerializedName("appearances")
        val appearances: String? = null,
        @SerializedName("client")
        val client: String? = null,
        @SerializedName("hero_380_image")
        val hero380Image: String? = null,
        @SerializedName("job_category")
        val jobCategory: String? = null,
        @SerializedName("report_at_address")
        val reportAtAddress: String? = null,
        @SerializedName("self")
        val self: String? = null,
        @SerializedName("skills")
        val skills: String? = null
    )

    data class Project(
        @SerializedName("archived_at")
        val archivedAt: Any? = null,
        @SerializedName("client")
        val client: Client? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("name")
        val name: String? = null
    ) {
        data class Client(
            @SerializedName("allow_temper_trial")
            val allowTemperTrial: Boolean? = null,
            @SerializedName("average_response_time")
            val averageResponseTime: Double? = null,
            @SerializedName("blocked_minutes_before_shift")
            val blockedMinutesBeforeShift: Any? = null,
            @SerializedName("description")
            val description: String? = null,
            @SerializedName("factoring_allowed")
            val factoringAllowed: Boolean? = null,
            @SerializedName("factoring_payment_term_in_days")
            val factoringPaymentTermInDays: Int? = null,
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("links")
            val links: Links? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("registration_id")
            val registrationId: String? = null,
            @SerializedName("registration_name")
            val registrationName: String? = null,
            @SerializedName("slug")
            val slug: String? = null
        ) {
            data class Links(
                @SerializedName("hero_image")
                val heroImage: String? = null,
                @SerializedName("thumb_image")
                val thumbImage: String? = null
            )
        }
    }

    data class ReportAtAddress(
        @SerializedName("city")
        val city: String? = null,
        @SerializedName("country")
        val country: Country? = null,
        @SerializedName("extra")
        val extra: String? = null,
        @SerializedName("geo")
        val geo: Geo? = null,
        @SerializedName("line1")
        val line1: String? = null,
        @SerializedName("line2")
        val line2: String? = null,
        @SerializedName("links")
        val links: Links? = null,
        @SerializedName("number")
        val number: String? = null,
        @SerializedName("number_with_extra")
        val numberWithExtra: String? = null,
        @SerializedName("region")
        val region: String? = null,
        @SerializedName("street")
        val street: String? = null,
        @SerializedName("zip_code")
        val zipCode: String? = null
    ) {
        data class Country(
            @SerializedName("human")
            val human: String? = null,
            @SerializedName("iso3166_1")
            val iso31661: String? = null
        )

        data class Geo(
            @SerializedName("lat")
            val lat: Double? = null,
            @SerializedName("lon")
            val lon: Double? = null
        )

        data class Links(
            @SerializedName("get_directions")
            val getDirections: String? = null
        )
    }

    data class ReportTo(
        @SerializedName("details")
        val details: Any? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("phone")
        val phone: String? = null
    )

    data class Skill(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("name")
        val name: String? = null
    )
}