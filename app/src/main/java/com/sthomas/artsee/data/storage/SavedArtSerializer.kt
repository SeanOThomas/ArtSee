package com.sthomas.artsee.data.storage

import androidx.datastore.core.Serializer
import com.sthomas.artsee.data.storage.dto.SavedArtDto
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object SavedArtSerializer : Serializer<SavedArtDto> {

    override val defaultValue: SavedArtDto
        get() = SavedArtDto()

    override suspend fun readFrom(input: InputStream): SavedArtDto {
        return try {
            Json.decodeFromString(
                deserializer = SavedArtDto.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: SavedArtDto, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = SavedArtDto.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}