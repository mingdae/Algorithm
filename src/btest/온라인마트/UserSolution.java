package btest.온라인마트;
import java.util.*;

class UserSolution{
 
    static class Product implements Comparable<Product>{
        int id;
        int catagory;
        int company;
        int price;
 
        public Product(int id, int catagory, int company, int price) {
            this.id = id;
            this.catagory = catagory;
            this.company = company;
            this.price = price;
        }
 
				// 가격이 같은 경우 상품 ID가 더 적은 값을 가진 상품이 우선
        @Override
        public int compareTo (Product o) {
            return this.price == o.price ? this.id - o.id : this.price - o.price;
        }
    }
 
    int[][] diff;
    Map<Integer, Product> products;
    TreeSet<Product>[][] productSet = new TreeSet[6][6];
    
    // 초기화
    public void init(){
        diff = new int[6][6]; // 카테고리-회사에 +-mAmount 가격 변동 저장
        products = new HashMap<>(); // 상품 해시맵으로 저장
        // 카테고리-회사 별로 트리셋 생성
        for (int category = 1; category <= 5; category++) {
            for (int company = 1; company <= 5; company++) {
                productSet[category][company] = new TreeSet<>();
            }
        }
 
        return;
    }
 
		// diff 는 누적합이므로 상품을 등록할때 
		// 그전에 쌓여있던 diff의 값을 더해줘야 mPrice 가격으로 저장되기에
		// mPrice - diff[mCategory][mCompany]
    public int sell(int mID, int mCategory, int mCompany, int mPrice){
        Product product = new Product(mID, mCategory, mCompany, mPrice - diff[mCategory][mCompany]);
        products.put(mID, product);
        productSet[mCategory][mCompany].add(product);
 
        return productSet[mCategory][mCompany].size();
    }
 
    public int closeSale(int mID){
        if (!products.containsKey(mID)) {
            return -1;
        }
 
        Product product = products.get(mID);
        products.remove(mID);
        productSet[product.catagory][product.company].remove(product);
        return product.price + diff[product.catagory][product.company];
    }
 
 
    public int discount(int mCategory, int mCompany, int mAmount){
        diff[mCategory][mCompany] -= mAmount;
 
				// productSet[mCategory][mCompany] 가 1개 이상 있고,
				// 가장 작은 상품의 최신 가격이 음수이거나 0일때
				// 해시맵과 트리셋에서 제거
        while (!productSet[mCategory][mCompany].isEmpty() 
                && productSet[mCategory][mCompany].first().price + diff[mCategory][mCompany] <= 0) {
            products.remove(productSet[mCategory][mCompany].first().id);
            productSet[mCategory][mCompany].pollFirst();
        }
        return productSet[mCategory][mCompany].size();
    }
 
    Solution.RESULT show(int mHow, int mCode){
        Solution.RESULT res = new Solution.RESULT();
        TreeSet<Product> recommand = new TreeSet<>();
 
        for (int category = 1; category <= 5; category++) {
            if (mHow == 1 && category != mCode) continue;
            for (int company = 1; company <= 5; company++) {
                if (productSet[category][company].isEmpty() || mHow == 2 && company != mCode) continue;
                
                Product product = productSet[category][company].first();
                while (product != null) {
                    int price = product.price + diff[category][company];
                    if (recommand.size() < 5) {
                        recommand.add(new Product(product.id, category, company, price));
                    } else {
                        int prevId = recommand.last().id;
                        int prevPrice = recommand.last().price;
 
                        if (prevPrice < price || (prevPrice == price && prevId < product.id)) break;
                        recommand.pollLast();
                        recommand.add(new Product(product.id, category, company, price));
                    }
 
                    product = productSet[category][company].higher(product);
                }
            }
        }
 
        res.cnt = 0;
        while (!recommand.isEmpty()) {
            res.IDs[res.cnt++] = recommand.pollFirst().id;
        }
 
        return res;
    }
}