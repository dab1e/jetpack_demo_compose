package com.example.jetpack_compose_demo.repo

class MyRepository {
    fun getList(): List<String> {
        return listImage
    }

    private val listImage = listOf<String>(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
        "https://love-shayari.co/wp-content/uploads/2021/10/sun-rise.jpg",
        "https://st.depositphotos.com/1179847/4067/i/450/depositphotos_40676241-stock-photo-barbados.jpg",
        "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg",
        "https://images.unsplash.com/photo-1490730141103-6cac27aaab94?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
        "https://images.unsplash.com/photo-1519337778422-6a8148ed5db4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQni3lK25Co5pDnUTzjoGPjWUXUmQsv_3VGIxlC_hI5N_v0j7kxKoF5u6XkPlBtNhgF_-w&usqp=CAU"
    )
}