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
        waitFor { findCatalogTitle("Official", subCatalogTitles) }
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
        setDropdownValue("METAL CHOICE", "10-kt. Yellow Gold [+\$240.00]")
        setImageSelectionValue("f_metal_finishv0020", "o_fi09v0001")
        nextStep("Metals")
        atNextStep("Sides")
        setTextInputValue("RING SIDE 1 DEGREE", "BA")
        setTextInputValue("RING SIDE 1 MAJOR RIBBON", "MIS")
        setTextInputValue("RING SIDE 1 SHIELD", "2000")
        setImageSelectionValue("f_ring_side_2_panel", "o_77090073v0001")
        setTextInputValue("RING SIDE 2 MAJOR RIBBON", "MATH")
        setTextInputValue("RING SIDE 2 SHIELD", "2000")
        nextStep("Sides")
        atNextStep("Personalization")
        openSearchableDropdown("f_yeardatev0005")
        findSearchableDropdownValue("f_yeardatev0005", "mo_c_yeardate_2013")
        setSearchableDropdownValue("f_yeardatev0005", "mo_c_yeardate_2013")
        setDropdownValue("FINGER SIZE", "10.5")
        setDropdownValue("INSIDE ENGRAVING", "Initials")
        isTextGroupDisplayed("f_inside_engravingv0010", "sf_inside_engravingv0034", "so_eninv0001")
        setTextGroupValue("f_inside_engravingv0010", "sf_inside_engravingv0034", "so_eninv0001", "JJB")
        setImageSelectionValue("f_erpv0001", "o_extendedaddtl_chgv0001")
        nextStep("Personalization")

        then: "Add to cart"
        atAddToCart()
        addToCart()

        then: "We are now ready to checkout."
        waitFor({
            $("title").jquery.html() == "Jostens - Checkout "
        })
        at ShoppingCartPage

        and: "Our product is in the cart."
        verifyProductInCart("Official-Large Signet [S770B]")


    }
}
