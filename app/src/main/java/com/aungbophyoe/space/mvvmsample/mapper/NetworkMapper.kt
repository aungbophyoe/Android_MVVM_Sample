package com.aungbophyoe.space.mvvmsample.mapper

import com.aungbophyoe.space.mvvmsample.rest.response.PhotoNetworkEntity
import com.aungbophyoe.space.mvvmsample.model.Photo
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<PhotoNetworkEntity, Photo> {
    override fun mapFromEntity(entity: PhotoNetworkEntity): Photo {
        return Photo(entity.id,entity.title,entity.url)
    }

    override fun mapToEntity(domainModel: Photo): PhotoNetworkEntity {
        return PhotoNetworkEntity(domainModel.id,domainModel.photoTitle,domainModel.photoUrl)
    }

    fun mapFromEntityList(entities : List<PhotoNetworkEntity>) : List<Photo>{
        return entities.map { mapFromEntity(it) }
    }
}