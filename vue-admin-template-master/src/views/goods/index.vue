<!-- ProductList.vue -->
<template>
  <div class="product-list-container">
    <div class="header">
      <h1>商品列表</h1>
      <div class="search-bar">
        <input
          v-model="searchQuery"
          type="text"
          placeholder="搜索商品..."
          class="search-input"
        />
        <button @click="searchProducts" class="search-button">搜索</button>
      </div>
    </div>

    <div class="filters">
      <select v-model="selectedCategory" @change="filterProducts" class="category-filter">
        <option value="">所有分类</option>
        <option v-for="category in categories" :key="category" :value="category">
          {{ category }}
        </option>
      </select>

      <select v-model="sortBy" @change="sortProducts" class="sort-filter">
        <option value="name">按名称排序</option>
        <option value="price-low">价格从低到高</option>
        <option value="price-high">价格从高到低</option>
      </select>
    </div>

    <div class="product-grid">
      <div
        v-for="product in productss"
        :key="product.id"
        class="product-card"
        @click="viewProduct(product.id)"
      >
        <div class="product-image">
          <img :src="product.goodsImg" :alt="product.goodsName" />
        </div>
        <div class="product-info">
          <h3 class="product-name">{{ product.goodsName }}</h3>
          <p class="product-description">{{ product.goodsTitle }}</p>
          <div class="product-meta">
            <span class="product-price">¥{{ product.goodsPrice }}</span>
          </div>
          <div class="product-actions">
            <button @click.stop="addToCart(product)" class="add-to-cart-btn">
              加入购物车
            </button>
          </div>
        </div>
      </div>
    </div>

<!--    <div v-if="filteredProducts.length === 0" class="no-products">-->
<!--      <p>暂无商品</p>-->
<!--    </div>-->
  </div>
</template>

<script>
import {goodsList} from "@/api/sekill/goods";

export default {
  name: 'ProductList',
  data() {
    return {
      searchQuery: '',
      selectedCategory: '',
      sortBy: 'name',
      products: [
        {
          id: 1,
          name: '无线蓝牙耳机',
          description: '高品质音效，降噪功能，续航长达20小时',
          price: 299.99,
          category: '电子产品',
          image: 'https://img14.360buyimg.com/n5/s720x720_jfs/t1/317549/12/18081/23211/6880998cF67dc9f63/3eb7b904635974a9.jpg.avif'
        },
        {
          id: 2,
          name: '智能手表',
          description: '健康监测，运动追踪，支持多种表盘',
          price: 599.99,
          category: '电子产品',
          image: 'https://img14.360buyimg.com/n5/s720x720_jfs/t1/287486/26/18162/210167/686f5862F824c21ad/aca85cecaa5e402e.jpg.avif'
        },
        {
          id: 3,
          name: '棉质T恤',
          description: '纯棉材质，舒适透气，多种颜色可选',
          price: 59.99,
          category: '服装',
          image: 'https://img14.360buyimg.com/n5/s720x720_jfs/t20260624/304836/12/13128/100339/6859814bF2aebf9bb/d3845f2082922f06.png.avif'
        },
        {
          id: 4,
          name: '运动鞋',
          description: '轻便舒适，防滑底，适合多种运动',
          price: 399.99,
          category: '鞋类',
          image: 'https://img11.360buyimg.com/n2/s480x480_jfs/t1/266627/15/17393/132060/67a577e6Fb9c55f81/e856c704f9b9a7d2.jpg'
        },
        {
          id: 5,
          name: '咖啡机',
          description: '一键制作，多种口味，自动清洁功能',
          price: 899.99,
          category: '家用电器',
          image: 'https://img14.360buyimg.com/n5/s720x720_jfs/t1/88413/14/47902/38919/672b47b6Fe13664ff/bd90d645bf82a244.jpg.avif'
        },
        {
          id: 6,
          name: '背包',
          description: '防水材质，多隔层设计，适合日常通勤',
          price: 199.99,
          category: '箱包',
          image: 'https://img13.360buyimg.com/n5/s720x720_jfs/t1/207937/34/28370/104726/63ae9e1bF98bc7241/03d872f56757d456.jpg'
        }
      ],
      categories: [],
      productss:[]
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(){
      goodsList().then(res=>{
        console.log(res.data)
        this.productss=res.data
      })
    },
    searchProducts() {
      // 搜索逻辑已在 computed 中处理
    },
    filterProducts() {
      // 过滤逻辑已在 computed 中处理
    },
    sortProducts() {
      // 排序逻辑已在 computed 中处理
    },
    viewProduct(productId) {
      this.$router.push(`/product/${productId}`);
    },
    addToCart(product) {
      // 这里可以调用购物车添加逻辑
      console.log('添加到购物车:', product);
      alert(`已将 ${product.name} 添加到购物车`);
    }
  }
}
</script>

<style scoped>
.product-list-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  flex-wrap: wrap;
  gap: 20px;
}

.header h1 {
  margin: 0;
  color: #333;
}

.search-bar {
  display: flex;
  gap: 10px;
}

.search-input {
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  min-width: 250px;
}

.search-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.search-button:hover {
  background-color: #0056b3;
}

.filters {
  display: flex;
  gap: 15px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.category-filter,
.sort-filter {
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: white;
  font-size: 16px;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}

.product-card {
  border: 1px solid #eee;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  background-color: white;
  cursor: pointer;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.product-image {
  height: 200px;
  overflow: hidden;
}

.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-info {
  padding: 20px;
}

.product-name {
  margin: 0 0 10px 0;
  font-size: 18px;
  color: #333;
}

.product-description {
  margin: 0 0 15px 0;
  color: #666;
  font-size: 14px;
  line-height: 1.5;
}

.product-meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.product-price {
  font-size: 20px;
  font-weight: bold;
  color: #e74c3c;
}

.product-category {
  background-color: #f0f0f0;
  padding: 5px 10px;
  border-radius: 20px;
  font-size: 12px;
  color: #666;
}

.product-actions {
  text-align: center;
}

.add-to-cart-btn {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.add-to-cart-btn:hover {
  background-color: #218838;
}

.no-products {
  text-align: center;
  grid-column: 1 / -1;
  padding: 50px;
  color: #999;
}

@media (max-width: 768px) {
  .header {
    flex-direction: column;
    align-items: stretch;
  }

  .search-bar {
    flex-direction: column;
  }

  .search-input {
    min-width: auto;
  }

  .filters {
    flex-direction: column;
  }

  .product-grid {
    grid-template-columns: 1fr;
  }
}
</style>
