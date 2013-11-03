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

        atAddToCart(wait: true) {
            return $("a.jos-add-to-cart").isDisplayed()
        }

        addToCart {
            def js = "return \$(\"a.jos-add-to-cart\").click().length"
            def updated = browser.js.exec(js)
            println "Updated " + updated + " | " + js
            if (updated == 1) {
                return true
            }
            return false
        }

    }
}
