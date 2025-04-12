package ru.dezerom.tasks.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.mappers.toDataModel
import ru.dezerom.tasks.data.network.models.TaskNetworkDto

class TaskDtoMapperTest {

    @Test
    fun mapTaskDto_allFields() {
        val dto = TaskNetworkDto(
            id = "1",
            name = "qwe",
            description = "asd",
            deadline = 1000,
            createdAt = 1000,
            isCompleted = true,
            completedAt = 1000
        )

        val model = dto.toDataModel()

        assertEquals(dto.id, model.id)
        assertEquals(dto.name, model.name)
        assertEquals(dto.description, model.description)
        assertEquals(dto.deadline, model.deadline)
        assertEquals(dto.createdAt, model.createdAt)
        assertEquals(dto.isCompleted, model.isCompleted)
        assertEquals(dto.completedAt, model.completedAt)
    }

    @Test
    fun mapTaskDto_notCompleted() {
        val dto = TaskNetworkDto(
            id = "1",
            name = "qwe",
            description = "asd",
            deadline = 1000,
            createdAt = 1000,
        )

        val model = dto.toDataModel()

        assertEquals(dto.id, model.id)
        assertEquals(dto.name, model.name)
        assertEquals(dto.description, model.description)
        assertEquals(dto.deadline, model.deadline)
        assertEquals(dto.createdAt, model.createdAt)

        assertFalse(model.isCompleted)
        assertNull(model.completedAt)
    }

    @Test
    fun mapTaskDto_nulls() {
        val dto = TaskNetworkDto()

        val model = dto.toDataModel()

        assertTrue(model.id.isEmpty())
        assertTrue(model.name.isEmpty())
        assertTrue(model.description.isEmpty())
        assertNull(model.deadline)
        assertEquals(0L, model.createdAt)
        assertFalse(model.isCompleted)
        assertNull(model.completedAt)
    }

}
