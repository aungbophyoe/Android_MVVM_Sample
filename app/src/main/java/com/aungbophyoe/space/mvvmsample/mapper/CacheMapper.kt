package com.aungbophyoe.space.mvvmsample.mapper

import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.room.PhotoCacheEntity
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<PhotoCacheEntity,Photo> {
    override fun mapFromEntity(entity: PhotoCacheEntity): Photo {
        return Photo(entity.id,entity.title,entity.url)
    }

    override fun mapToEntity(domainModel: Photo): PhotoCacheEntity {
        return PhotoCacheEntity(domainModel.id,domainModel.photoTitle,domainModel.photoUrl)
    }

    fun mapFromEntityList(entities : List<PhotoCacheEntity>) : List<Photo>{
        return entities.map { mapFromEntity(it) }
    }
}