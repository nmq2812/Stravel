package com.example.stravel.components

import com.example.stravel.R

data class PlaceItem(
    val name: String,
    val id: Int,
    val image: Int,
    val description: String,
    val score: Int,
    var favou: Boolean
)

fun setFavou(Item: PlaceItem) {
    Item.favou = !Item.favou
}

val listOfPlaceItems = listOf(
    PlaceItem(
        name = "Nhà thờ lớn",
        id = 1,
        image = R.drawable.nhatholon,
        description = "Nhà thờ lớn ở Việt Nam thường được biết đến với tên gọi chính thức là Nhà thờ Chính tòa Đức Bà, là một trong những công trình tôn giáo lớn và độc đáo nhất của đất nước. Đặt tại trung tâm thành phố lớn như Hà Nội, Hồ Chí Minh, Huế và nhiều thành phố khác, những nhà thờ này thường mang trong mình cả giá trị tôn giáo lâu đời và nét kiến trúc đặc sắc.\n" +
                "\n" +
                "Nhà thờ lớn thường là biểu tượng của sự kết hợp giữa kiến trúc Pháp và văn hóa dân tộc Việt Nam. Được xây dựng từ thế kỷ 19 dưới thời thuộc địa Pháp, những công trình này thường có kiến trúc vững chãi, to lớn với các cột trụ, cửa sổ và cúpola ấn tượng. Ngoài ra, những đặc điểm kiến trúc của Việt Nam như hình dạng mái ngói cong, cột gỗ tròn cũng thường được thêm vào để tạo ra sự hòa quyện hài hòa.\n" +
                "\n" +
                "Ngoài tính chất tôn giáo, những nhà thờ lớn còn là điểm đến văn hóa và du lịch quan trọng. Với kiến trúc đặc biệt, những nơi này thu hút rất nhiều du khách đến tham quan, khám phá và chiêm ngưỡng. Đồng thời, chúng cũng là nơi tôn vinh và bảo tồn di sản văn hóa của Việt Nam.",
        score = 4,
        favou = true
    ),
    PlaceItem(
        name = "Chùa một cột",
        id = 2,
        image = R.drawable.chuamotcot,
        description = "Chùa Một Cột, hay còn được gọi là Chùa Diên Hựu, là một trong những ngôi chùa nổi tiếng ở Hà Nội, Việt Nam. Tọa lạc tại khu vực phía Nam của Hồ Hoàn Kiếm, ngay cạnh công viên Diên Hựu, chùa này nổi bật với kiến trúc độc đáo và câu chuyện lịch sử sâu sắc."+
                "\n" +
                " Chùa Một Cột được xây dựng vào đầu thế kỷ 11 bởi vua Lý Thái Tông, theo truyền thuyết là sau khi ông mơ thấy Đức Phật Quan Âm ngồi trên hoa sen, ông đã lập tức quyết định xây dựng chùa này. Kiến trúc của chùa được thiết kế như một ngôi nhà trên một cột duy nhất, từ đó mang tên gọi Một Cột. "+
                "\n" +
                "Đến ngày nay, Chùa Một Cột vẫn thu hút đông đảo du khách cả trong và ngoài nước bởi vẻ đẹp yên bình, nét kiến trúc độc đáo và giá trị văn hóa lịch sử sâu sắc. Đây là một điểm đến không thể bỏ qua khi khám phá văn hóa và lịch sử của thủ đô Hà Nội cùng với những chứng tích của đất nước Việt Nam.",
        score = 4,
        favou = true

    ),
    PlaceItem(
        name = "Văn Miếu - Quốc Tử Giám",
        id = 3,
        image = R.drawable.vanmieu,
        description = "Văn Miếu Quốc Tử Giám là một trong những di tích lịch sử văn hóa quan trọng nhất của Việt Nam. Được xây dựng vào thế kỷ 11 dưới triều đại của Vua Lý Thánh Tông, Văn Miếu Quốc Tử Giám tại Hà Nội là nơi thờ cúng và tôn vinh các nhà sư phạm và học者 nổi tiếng của đất nước.\n" +
                "\n" +
                "Điểm đặc biệt của Văn Miếu là ngôi Đền của Thiên Thai, nơi thờ cúng Khổng Tử, người sáng lập ra triết học Nho giáo, cùng với các nhà giáo lớn khác như Công Tôn Toản, Chu Văn An, và nhiều danh nhân khác trong lịch sử Việt Nam.\n" +
                "\n" +
                "Ngoài việc thờ cúng, Văn Miếu Quốc Tử Giám còn là nơi lưu giữ hàng ngàn bia tiến sĩ, ghi chép tên tuổi và thành tựu của những người đã đỗ đạt các kỳ thi triều đình. Điều này thể hiện tầm quan trọng của giáo dục trong xã hội truyền thống Việt Nam.\n" +
                "\n" +
                "Văn Miếu Quốc Tử Giám không chỉ là một điểm tham quan lịch sử quan trọng mà còn là biểu tượng của truyền thống học văn và tinh thần học thuật của người Việt Nam.",
        score = 4,
        favou = true

    ),
    PlaceItem(
        name = "Hồ Gươm",
        id = 4,
        image = R.drawable.hoguom,
        description = "Hồ Gươm, còn được gọi là Hồ Hoàn Kiếm, là một biểu tượng lịch sử và văn hóa quan trọng của Việt Nam, nằm ở trung tâm thủ đô Hà Nội. Hồ Gươm được hình thành từ thời kỳ Lê - Mạc (thế kỷ 15) và là nơi diễn ra nhiều sự kiện lịch sử, văn hóa quan trọng của dân tộc Việt Nam.\n" +
                "\n" +
                "Tên \"Hoàn Kiếm\" xuất phát từ một câu chuyện lịch sử và huyền thoại về vị anh hùng Lê Lợi, người đã đánh bại quân Minh xâm lược và khôi phục độc lập cho đất nước. Theo truyền thuyết, Lê Lợi đã nhận được một thanh kiếm quý giá từ Rồng Hạ Long khiến ông trở thành vị vua. Sau khi đánh bại quân Minh, ông trở lại Hồ Gươm và trả thanh kiếm cho Rồng Hạ Long. Từ đó, hồ được gọi là \"Hồ Hoàn Kiếm\", có nghĩa là \"Hồ của Thanh Kiếm\".\n" +
                "\n" +
                "Ngoài vai trò lịch sử, Hồ Gươm còn là trung tâm của cuộc sống văn hóa và du lịch tại Hà Nội. Với bờ hồ rộng lớn, cây cỏ xanh mát và kiến trúc xung quanh pha trộn giữa cổ điển và hiện đại, hồ thu hút hàng triệu du khách mỗi năm đến thăm quan và thưởng ngoạn vẻ đẹp tinh tế của nó. Đây cũng là nơi diễn ra nhiều hoạt động văn hóa, lễ hội truyền thống và sự kiện quan trọng của thủ đô Hà Nội.",
        score = 4,
        favou = true

    ),
    PlaceItem(
        name = "Lăng Chủ Tịch Hồ Chí Minh",
        id = 5,
        image = R.drawable.langchutich,
        description = "Lăng Chủ tịch Hồ Chí Minh là một trong những địa điểm lịch sử và văn hóa quan trọng nhất ở Việt Nam. Được xây dựng tại thành phố Hà Nội, nơi mà Chủ tịch Hồ Chí Minh đã làm việc và sinh sống trong những năm cuối đời, lăng mộ này không chỉ là nơi an nghỉ cuối cùng của người lãnh đạo vĩ đại này mà còn là một điểm tham quan lịch sử thu hút hàng triệu du khách mỗi năm.\n" +
                "\n" +
                "Lăng Chủ tịch Hồ Chí Minh được xây dựng theo kiến trúc hiện đại, nhưng vẫn mang đậm nét văn hóa truyền thống của Việt Nam. Nó bao gồm nhiều công trình kiến trúc quan trọng như lăng mộ, nhà Bác Hồ, và khu vườn xung quanh, tạo nên một không gian trang nghiêm và tĩnh lặng.\n" +
                "\n" +
                "Bên trong lăng mộ là nơi lưu giữ di tích và hồi ức về cuộc đời và sự nghiệp của Chủ tịch Hồ Chí Minh. Du khách có thể thăm quan các phòng trưng bày với các hiện vật, hình ảnh và tư liệu liên quan đến cuộc sống và công việc của Người.\n" +
                "\n" +
                "Ngoài vai trò là một địa điểm tưởng niệm, Lăng Chủ tịch Hồ Chí Minh còn là biểu tượng của lòng kính trọng và tôn trọng sâu sắc của nhân dân Việt Nam dành cho người đại diện lớn lao của họ.",
        score = 4,
        favou = false

    ),
    PlaceItem(
        name = "Hoàng Thành Thăng Long",
        id = 6,
        image = R.drawable.hoangthanh,
        description = "Hoàng thành Thăng Long, còn được biết đến với tên gọi Hoàng thành Huế, là một di tích lịch sử và văn hóa quan trọng tại Việt Nam. Nằm ở trung tâm của thủ đô Hà Nội, Hoàng thành Thăng Long là một trong những kỳ quan kiến trúc hàng đầu của đất nước, đồng thời cũng là biểu tượng lịch sử của sự phồn thịnh và văn minh của người Việt từ thế kỷ 11 đến thế kỷ 19.\n" +
                "\n" +
                "Ban đầu được xây dựng vào thế kỷ 11 trong triều đại Lý, Hoàng thành Thăng Long trải qua nhiều giai đoạn phát triển và sửa chữa trong suốt lịch sử. Đây là nơi đặt trụ sở của các triều đại vương quốc như Lý, Trần, Lê và Nguyễn. Hoàng thành Thăng Long không chỉ là kinh đô của đất nước mà còn là trung tâm văn hóa, chính trị và tôn giáo của Việt Nam.\n" +
                "\n" +
                "Với kiến trúc hùng vĩ và sự phức tạp trong cách bố trí các công trình, Hoàng thành Thăng Long là một điểm đến thu hút không chỉ người dân Việt Nam mà còn khách du lịch quốc tế. Năm 2010, nó đã được UNESCO công nhận là Di sản Văn hóa Thế giới, vinh danh giá trị lịch sử và văn hóa của Việt Nam trên trường quốc tế.",
        score = 4,
        favou = true
    ),
)
