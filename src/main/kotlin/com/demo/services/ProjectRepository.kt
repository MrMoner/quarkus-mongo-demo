package com.demo.services

import com.demo.domain.Project
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProjectRepository : PanacheMongoRepository<Project>