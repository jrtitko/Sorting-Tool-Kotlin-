fun solution(products: List<String>, product: String) {
    for (p in products.indices) {
        if (products[p] == product) print("$p ")
    }
}

