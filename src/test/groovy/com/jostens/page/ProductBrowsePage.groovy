package com.jostens.page

import geb.Page

class ProductBrowsePage extends Page {

    static url = "apps/catalog/college/productBrowse"
    static at = { title == title } // Hack - Product Browse page title seems to randomly change.  Thread safety?

    static content = {

        clickProductLink { productId ->
            $("li[data-jos-product-sku='" + productId + "'].jos-productgrid-link a").click()
        }
    }

}
