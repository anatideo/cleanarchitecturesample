package com.anatideo.cleanarchitecture.android.core.domain

/**
 * This class can be used to map a type [IN] to a new type [OUT].
 */
abstract class BaseMapper<IN, OUT> {
    /**
     * Maps a single [entity]
     */
    abstract fun transform(entity: IN): OUT
    /**
     * Maps a list of [entities].
     */
    fun transform(entities: List<IN>): List<OUT> {
        return entities.map(::transform)
    }
}