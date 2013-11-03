package com.jostens.page

import geb.Page

class ShoppingCartPage extends Page {

    static url = "apps/checkout/shoppingCart.mvc"
    static at = { title == "Jostens - Checkout" }

    static content = {

        itemNames { $("span.itemName") }

        verifyProductInCart { productName ->
            def itemName = null
            for (item in itemNames) {
                if (productName.equals(item.jquery.html())) {
                    itemName = item
                    break
                }
            }
            return itemName
        }

    }

}
