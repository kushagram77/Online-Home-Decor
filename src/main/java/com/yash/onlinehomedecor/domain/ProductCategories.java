package com.yash.onlinehomedecor.domain;
/**
 *
 * @author Kushagra Mishra
 */
public class ProductCategories {
        private Integer id;
        private String name;
        private String created_by;
        private Integer shop_id;

        public Integer getShop_id() {
                return shop_id;
        }

        public void setShop_id(Integer shop_id) {
                this.shop_id = shop_id;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCreated_by() {
                return created_by;
        }

        public void setCreated_by(String created_by) {
                this.created_by = created_by;
        }
}
