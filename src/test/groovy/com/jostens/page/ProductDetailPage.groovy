package com.jostens.page

import geb.Page

class ProductDetailPage extends Page {

    static url = "apps/catalog/college/productDetail"
    static at = { title == title } // Hack.  They change.

    static content = {

        clickCustomize(wait: true) {
            $("a.jos-customize").click()
        }

        setDropdownValue {featureOsr, optionDisplayName ->
            def select = $(".product-feature-container select[data-jos-feature-osr='" + featureOsr + "']")
            select.jquery.val(optionDisplayName);

            def js = "\$(\".product-feature-container select[data-jos-feature-osr='" + featureOsr + "']\").trigger('change');"
            browser.js.exec(js)

            return select
        }

        setImageSelectionValue {featureId, optionId ->
            $(".product-feature-container fieldset#" + featureId + " ul.jos-image-selection-option-container li#" + optionId).click()
        }

        setTextInputValue {featureOsr, optionValue ->
            def js = "return \$(\".product-feature-container input[data-jos-feature-osr='" + featureOsr + "']\").val('" + optionValue + "').length;"
            def updated = browser.js.exec(js)
            if (updated == 1) {
                js = "\$(\".product-feature-container input[data-jos-feature-osr='" + featureOsr + "']\").trigger('change');"
                browser.js.exec(js)
                return true
            }
            return false
        }

        openSearchableDropdown { featureId ->
            $(".product-feature-container fieldset#" + featureId + " .jos-searchable-dropdown-input").click()
        }

        findSearchableDropdownValue(wait: true) { featureId, optionId ->
            $(".product-feature-container fieldset#" + featureId + " li#" + optionId)
        }

        setSearchableDropdownValue { featureId, optionId ->
            findSearchableDropdownValue(featureId, optionId).jquery.mousedown()
        }

        isTextGroupDisplayed(wait: true) { featureId, subFeatureId, subOptionId ->
            $(".product-feature-container[data-jos-feature-id='" + featureId + "'] input[data-jos-role='SUB_FEATURE'][data-jos-feature-id='" + subFeatureId + "'][data-jos-option-id='" + subOptionId + "']")
        }

        setTextGroupValue { featureId, subFeatureId, subOptionId, optionValue ->
            def js = "return \$(\".product-feature-container[data-jos-feature-id='" + featureId + "'] input[data-jos-role='SUB_FEATURE'][data-jos-feature-id='" + subFeatureId + "'][data-jos-option-id='" + subOptionId + "']\").val('" + optionValue + "').length;"
            def updated = browser.js.exec(js)
            if (updated == 1) {
                js = "\$(\".product-feature-container[data-jos-feature-id='" + featureId + "'] input[data-jos-role='SUB_FEATURE'][data-jos-feature-id='" + subFeatureId + "'][data-jos-option-id='" + subOptionId + "']\").trigger('change');"
                browser.js.exec(js)
                return true
            }
            return false
        }

        nextStep {stepOsr ->
            $("div.step[data-jos-step-osr='" + stepOsr + "'] a.jos-next").click()
        }

        atNextStep(wait: true) {stepOsr ->
            return $("div.step[data-jos-step-osr='" + stepOsr + "'] a.jos-next").isDisplayed()
        }

        designProduct { product ->
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
        }

        atAddToCart(wait: true) {
            return $("a.jos-add-to-cart").isDisplayed()
        }

        addToCart {
            atAddToCart()
            def js = "return \$(\"a.jos-add-to-cart\").click().length"
            def updated = browser.js.exec(js)
            println "Updated " + updated + " | " + js
            if (updated == 1) {
                waitFor({
                    $("title").jquery.html() == "Jostens - Checkout "
                })
                return true
            }
            return false
        }

    }
}
