package com.demo

import com.demo.domain.Project
import com.demo.services.ProjectRepository
import io.quarkus.panache.common.Parameters
import org.bson.Document
import org.bson.types.ObjectId
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("api")
class ProjectResource {

    @Inject
    lateinit var projectRepository: ProjectRepository

    @Path("projects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllProjects(): List<Project> = projectRepository.listAll()

    @Path("project/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getProject(
        @PathParam("id") id: String
    ): Project? = projectRepository.findById(ObjectId(id))

    @Path("project")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun searchProjects(
        @QueryParam("name") projectName: String
    ): List<Project> {
        val parameters = Parameters()
        parameters.and("name", projectName)
        return projectRepository.find(
            "{}", parameters
        ).list()
    }

    @Path("project")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createProject(project: Project): String {
        project.persist()
        return project.id?.toString() ?: "Created"
    }

    @Path("project/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    fun deleteProject(
        @PathParam("id") id: String
    ) = mapOf("success" to projectRepository.deleteById(ObjectId(id)))
}