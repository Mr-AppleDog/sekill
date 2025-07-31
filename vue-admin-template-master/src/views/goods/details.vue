<!-- E:\workspace-github\Second-kill\vue-admin-template-master\src\views\goods\details.vue -->
<template>
  <div class="goods-details-container">
    <el-card v-if="goodsInfo" class="goods-card">
      <div class="goods-header">
        <el-page-header @back="goBack" content="商品详情" />
      </div>

      <el-row :gutter="30" class="goods-content">
        <el-col :span="10">
          <div class="goods-image">
            <el-image
              :src="goodsInfo.goodsImg"
              :preview-src-list="[goodsInfo.goodsImg]"
              fit="cover"
              class="goods-img">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
                <span>暂无图片</span>
              </div>
            </el-image>
          </div>
        </el-col>

        <el-col :span="14">
          <div class="goods-info">
            <h1 class="goods-name">{{ goodsInfo.goodsName }}</h1>
            <p class="goods-title">{{ goodsInfo.goodsTitle }}</p>

            <div class="price-section">
              <div class="original-price">
                原价: <span class="price">¥{{ goodsInfo.goodsPrice }}</span>
              </div>
              <div class="seckill-price" v-if="goodsInfo.killPrice">
                秒杀价: <span class="price seckill">¥{{ goodsInfo.killPrice }}</span>
              </div>
            </div>

            <div class="seckill-time-info">
              <el-alert
                v-if="seckillStatus === 'pending'"
                :title="`秒杀活动即将开始，倒计时: ${countdownText}`"
                type="info"
                show-icon
                :closable="false">
              </el-alert>
              <el-alert
                v-else-if="seckillStatus === 'active'"
                :title="`秒杀活动进行中，剩余时间: ${countdownText}`"
                type="success"
                show-icon
                :closable="false">
              </el-alert>
              <el-alert
                v-else-if="seckillStatus === 'expired'"
                title="秒杀活动已结束"
                type="warning"
                show-icon
                :closable="false">
              </el-alert>
            </div>

            <div class="goods-meta">
              <el-descriptions :column="1" size="medium" border>
                <el-descriptions-item label="商品ID">{{ goodsInfo.id }}</el-descriptions-item>
                <el-descriptions-item label="商品分类">{{ goodsInfo.goodsType || '未分类' }}</el-descriptions-item>
                <el-descriptions-item label="秒杀库存">{{ goodsInfo.killStock }}</el-descriptions-item>
                <el-descriptions-item label="开始时间">{{ goodsInfo.startTime }}</el-descriptions-item>
                <el-descriptions-item label="结束时间">{{ goodsInfo.endTime }}</el-descriptions-item>
              </el-descriptions>
            </div>

            <div class="action-section">
              <el-button
                type="primary"
                size="large"
                :disabled="!isSeckillActive"
                @click="seckillGoods">
                {{ seckillButtonText }}
              </el-button>
              <span class="stock-info" v-if="goodsInfo.killStock > 0">
                剩余库存: {{ goodsInfo.killStock }} 件
              </span>
              <span class="stock-info out-of-stock" v-else>
                秒杀库存已售罄
              </span>
            </div>
          </div>
        </el-col>
      </el-row>

      <el-row class="goods-detail-section">
        <el-col :span="24">
          <el-divider>商品详情</el-divider>
          <div class="goods-detail-content" v-html="goodsInfo.goodsDetail || '暂无商品详情'"></div>
        </el-col>
      </el-row>
    </el-card>

    <el-card v-else class="loading-card">
      <el-skeleton :rows="8" animated />
    </el-card>
  </div>
</template>

<script>

import {doSeKill, goodsById} from "@/api/sekill/goods";

export default {
  name: 'Details',
  data() {
    return {
      goodsInfo: null,
      loading: true,
      countdownTimer: null,
      countdownText: ''
    }
  },
  computed: {
    // 计算秒杀状态
    seckillStatus() {
      if (!this.goodsInfo) return 'pending';

      const now = new Date();
      const startTime = new Date(this.goodsInfo.startTime);
      const endTime = new Date(this.goodsInfo.endTime);

      if (now < startTime) {
        return 'pending'; // 活动未开始
      } else if (now >= startTime && now <= endTime) {
        return 'active'; // 活动进行中
      } else {
        return 'expired'; // 活动已结束
      }
    },

    // 是否可以秒杀（活动进行中且有库存）
    isSeckillActive() {
      return this.seckillStatus === 'active' && this.goodsInfo.killStock > 0;
    },

    // 按钮文本
    seckillButtonText() {
      switch (this.seckillStatus) {
        case 'pending':
          return '秒杀未开始';
        case 'expired':
          return '秒杀已结束';
        case 'active':
          return this.goodsInfo.killStock > 0 ? '立即秒杀' : '秒杀已售罄';
        default:
          return '立即秒杀';
      }
    }
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    if (id) {
      this.get(id);
    }
  },
  beforeDestroy() {
    // 清除定时器
    if (this.countdownTimer) {
      clearInterval(this.countdownTimer);
    }
  },
  methods: {
    get(id) {
      this.loading = true;
      goodsById(id).then(res => {
        this.goodsInfo = res.data;
        this.loading = false;
        this.startCountdown(); // 开始倒计时
      }).catch(err => {
        this.loading = false;
        this.$message.error('获取商品详情失败: ' + (err.message || '未知错误'));
      });
    },
    goBack() {
      this.$router.go(-1);
    },
    // 开始倒计时
    startCountdown() {
      // 清除之前的定时器
      if (this.countdownTimer) {
        clearInterval(this.countdownTimer);
      }

      // 每秒更新一次倒计时
      this.updateCountdown();
      this.countdownTimer = setInterval(() => {
        this.updateCountdown();
      }, 1000);
    },

    // 更新倒计时文本
    updateCountdown() {
      if (!this.goodsInfo) return;

      const now = new Date();
      const startTime = new Date(this.goodsInfo.startTime);
      const endTime = new Date(this.goodsInfo.endTime);

      let targetTime;
      if (now < startTime) {
        targetTime = startTime; // 距离开始时间
        this.countdownText = this.formatCountdown(now, targetTime);
      } else if (now >= startTime && now <= endTime) {
        targetTime = endTime; // 距离结束时间
        this.countdownText = this.formatCountdown(now, targetTime);
      } else {
        // 活动已结束
        this.countdownText = '活动已结束';
        if (this.countdownTimer) {
          clearInterval(this.countdownTimer);
          this.countdownTimer = null;
        }
      }
    },

    // 格式化倒计时显示
    formatCountdown(now, targetTime) {
      const diff = targetTime - now;
      if (diff <= 0) return '0天0时0分0秒';

      const days = Math.floor(diff / (1000 * 60 * 60 * 24));
      const hours = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((diff % (1000 * 60)) / 1000);

      return `${days}天${hours}时${minutes}分${seconds}秒`;
    },

    seckillGoods() {
      if (!this.goodsInfo) return;

      // 只有在活动进行中且有库存时才能点击
      if (this.seckillStatus === 'active' && this.goodsInfo.killStock > 0) {
        doSeKill(this.goodsInfo.id).then().then(res => {
          this.$modal.msgSuccess("创建订单");
        }).catch(err => {
          console.log(err);
        });
        // 这里可以添加实际的秒杀逻辑
        // 例如跳转到确认页面: this.$router.push(`/seckill/confirm/${this.goodsInfo.id}`);
      }
    }
  }
}
</script>

<style scoped lang="scss">
.goods-details-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: calc(100vh - 84px);
}

.goods-card {
  margin-bottom: 20px;
}

.goods-header {
  margin-bottom: 20px;
}

.goods-content {
  margin-bottom: 30px;
}

.goods-image {
  .goods-img {
    width: 100%;
    height: 400px;
    border-radius: 8px;
    overflow: hidden;

    ::v-deep .image-slot {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 100%;
      background: #f5f7fa;
      color: #909399;

      i {
        font-size: 30px;
        margin-bottom: 10px;
      }
    }
  }
}

.goods-info {
  .goods-name {
    font-size: 24px;
    color: #303133;
    margin-bottom: 10px;
  }

  .goods-title {
    font-size: 16px;
    color: #606266;
    margin-bottom: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #ebeef5;
  }

  .price-section {
    margin-bottom: 20px;

    .original-price {
      font-size: 16px;
      color: #909399;
      margin-bottom: 10px;
    }

    .seckill-price {
      font-size: 18px;
      margin-bottom: 15px;
    }

    .price {
      font-size: 24px;
      font-weight: bold;

      &.seckill {
        color: #fa541c;
      }
    }
  }

  .seckill-time-info {
    margin-bottom: 20px;
  }

  .goods-meta {
    margin-bottom: 30px;
  }

  .action-section {
    .el-button {
      width: 200px;
      height: 50px;
      font-size: 18px;
      margin-right: 20px;
    }

    .stock-info {
      font-size: 16px;
      color: #67c23a;

      &.out-of-stock {
        color: #f56c6c;
      }
    }
  }
}

.goods-detail-section {
  margin-top: 20px;

  .goods-detail-content {
    padding: 20px;
    background-color: #fff;
    border-radius: 4px;
    min-height: 200px;

    ::v-deep img {
      max-width: 100%;
      height: auto;
    }

    ::v-deep p {
      line-height: 1.8;
      margin: 10px 0;
    }
  }
}

.loading-card {
  min-height: 500px;
}

::v-deep .el-descriptions__body {
  background-color: #fafafa;
}
</style>
