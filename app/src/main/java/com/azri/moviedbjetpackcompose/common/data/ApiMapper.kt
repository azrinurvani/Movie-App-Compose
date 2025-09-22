package com.azri.moviedbjetpackcompose.common.data

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto: Entity) : Domain
}