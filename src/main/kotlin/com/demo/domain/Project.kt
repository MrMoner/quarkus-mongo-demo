package com.demo.domain

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity
import java.time.LocalDate

@MongoEntity
data class Project (
    var projectName: String,
    var projectStartDate: LocalDate
): PanacheMongoEntity() {
    constructor() : this(
        "projectName", LocalDate.MIN
    )
}