package benchmarks

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.domain.responses.DemoObjectResponse
import com.vnteam.architecturetemplates.domain.responses.OwnerResponse
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI

object MapperTestData {
    fun createTestDemoObject(): DemoObject = DemoObject(
        demoObjectId = "test_id_123",
        name = "Test Object",
        owner = Owner(
            ownerId = "owner_id_456",
            login = "testuser",
            avatarUrl = "https://example.com/avatar.jpg",
            url = "https://example.com/profile"
        ),
        htmlUrl = "https://example.com/object",
        description = "Test description"
    )

    fun createTestDemoObjectList(size: Int): List<DemoObject> =
        List(size) { index ->
            createTestDemoObject().copy(
                demoObjectId = "id_$index",
                name = "Object $index"
            )
        }

    fun createTestDemoObjectResponse(): DemoObjectResponse = DemoObjectResponse(
        demoObjectId = "test_id_123",
        name = "Test Object",
        owner = OwnerResponse(
            ownerId = "owner_id_456",
            login = "testuser",
            avatarUrl = "https://example.com/avatar.jpg",
            url = "https://example.com/profile"
        ),
        htmlUrl = "https://example.com/object",
        description = "Test description"
    )

    fun createTestOwnerResponse(): OwnerResponse = OwnerResponse(
        ownerId = "owner_id_456",
        login = "testuser",
        avatarUrl = "https://example.com/avatar.jpg",
        url = "https://example.com/profile"
    )

    fun createTestOwnerUI(): OwnerUI = OwnerUI(
        ownerId = "owner_id_456",
        login = "testuser",
        avatarUrl = "https://example.com/avatar.jpg",
        url = "https://example.com/profile"
    )
}