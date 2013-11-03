package com.jostens.spec

import com.jostens.page.CustomerPage
import com.jostens.page.ProductBrowsePage
import com.jostens.page.ProductDetailPage
import com.jostens.page.ShoppingCartPage
import geb.spock.GebReportingSpec

class JostensStoreSpec extends GebReportingSpec {

    def "I should be able to navigate to the school store page and purchase products."() {

        when: "Navigate to the school store page."
        to CustomerPage, "1041120", "University-of-Tennessee"

        then: "We have successfully loaded the page."
        at CustomerPage

        then: "Launch a top level catalog."
        clickCatalogLink("Class Rings")

        and: "Select a sub-catalog to browse."
        clickSubCatalogLink("Official")

        then: "We have arrived at the product browse page."
        at ProductBrowsePage

        and: "Select a product to design."
        clickProductLink("s7709055el")

        then: "We have arrived at the product detail page."
        at ProductDetailPage

        then: "Customize the product."
        clickCustomize()

        then: "Begin designing."
        designProduct()

        then: "Add to cart"
        addToCart()

        then: "We are now ready to checkout."
        at ShoppingCartPage

        and: "Our product is in the cart."
        verifyProductInCart("Official-Large Signet [S770B]")


    }
}
