package com.jostens.page

import geb.Page

class CustomerPage extends Page {

    static url = "apps/catalog/college/customer"
    static at = { title == "University of Tennessee Knoxville, TN - Yearbooks, Grad Products & Jewelry - Jostens" }

    static content = {
        catalogTitles { $(".jos-catalog-grid-link .title") }

        subCatalogTitles(wait: true) { $(".jos-sub-catalogs li span") }

        findCatalogTitle { catalogName, catalogTitles ->
            def catalogTitle = null
            for (title in catalogTitles) {
                if (catalogName.equals(title.jquery.html())) {
                    catalogTitle = title;
                    break;
                }
            }
            return catalogTitle;
        }

        clickCatalogLink { catalogName ->
            def catalogLink = findCatalogTitle(catalogName, catalogTitles).parent()
            catalogLink.click()
        }

        clickSubCatalogLink { catalogName ->
            def catalogLink = findCatalogTitle(catalogName, subCatalogTitles).parent()
            catalogLink.click()
        }
    }

}
