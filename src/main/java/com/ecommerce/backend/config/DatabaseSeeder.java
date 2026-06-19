package com.ecommerce.backend.config;

import com.ecommerce.backend.entity.Brand;
import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.BrandRepository;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public DatabaseSeeder(CategoryRepository categoryRepository, 
                          BrandRepository brandRepository, 
                          ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCategories();
        seedBrands();
        seedProducts();
    }

    private void seedCategories() {
        if (categoryRepository.count() == 0) {
            log.info("Seeding categories...");
            List<Category> categories = List.of(
                new Category(1L, "Laptop", "Gunluk kullanim ve profesyonel is akislari icin performans odakli laptoplar."),
                new Category(2L, "Akilli Telefon", "Yuksek kamera kalitesi, hizli islemci ve uzun pil omru sunan telefonlar."),
                new Category(3L, "Ses Sistemleri", "Kulaklik, bluetooth hoparlor ve muzik keyfini artiran ses ekipmanlari."),
                new Category(4L, "Aksesuar", "Gunluk kullanimda verimliligi artiran sarj, klavye ve diger tamamlayicilar."),
                new Category(5L, "Tablet", "Not alma, cizim ve tasinabilir eglence icin hafif ve guclu tabletler."),
                new Category(6L, "Oyun Ekipmanlari", "Oyuncu klavyesi, mouse, gamepad ve dusuk gecikmeli cevre birimleri."),
                new Category(7L, "Akilli Saat", "Saglik takibi, bildirim yonetimi ve spor odakli akilli saat modelleri."),
                new Category(8L, "Monitor", "Yuksek tazeleme hizi, renk dogrulugu ve farkli panel tipleri sunan monitorler."),
                new Category(9L, "Kamera ve Drone", "4K cekim, stabilizasyon ve havadan goruntu alma icin kamera cozumleri."),
                new Category(10L, "Ag ve Depolama", "Router, mesh sistemler, NAS ve harici depolama urunleri.")
            );
            categoryRepository.saveAll(categories);
            log.info("Categories seeded successfully.");
        }
    }

    private void seedBrands() {
        if (brandRepository.count() == 0) {
            log.info("Seeding brands...");
            List<Brand> brands = List.of(
                new Brand(1L, "NovaTech", "https://dummyimage.com/120x40/1a237e/ffffff&text=NovaTech"),
                new Brand(2L, "PulseOne", "https://dummyimage.com/120x40/263238/ffffff&text=PulseOne"),
                new Brand(3L, "Arclight", "https://dummyimage.com/120x40/00695c/ffffff&text=Arclight"),
                new Brand(4L, "Vertex", "https://dummyimage.com/120x40/6a1b9a/ffffff&text=Vertex"),
                new Brand(5L, "HelioWare", "https://dummyimage.com/120x40/1565c0/ffffff&text=HelioWare"),
                new Brand(6L, "QuantumEdge", "https://dummyimage.com/120x40/37474f/ffffff&text=QuantumEdge"),
                new Brand(7L, "IronPeak", "https://dummyimage.com/120x40/2e7d32/ffffff&text=IronPeak"),
                new Brand(8L, "Lumina", "https://dummyimage.com/120x40/5d4037/ffffff&text=Lumina"),
                new Brand(9L, "CoreSphere", "https://dummyimage.com/120x40/0277bd/ffffff&text=CoreSphere"),
                new Brand(10L, "SkyGrid", "https://dummyimage.com/120x40/455a64/ffffff&text=SkyGrid")
            );
            brandRepository.saveAll(brands);
            log.info("Brands seeded successfully.");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            log.info("Seeding products...");
            List<Product> products = List.of(
                new Product(101L, "NovaBook Air 14", new BigDecimal("32999"), "Ince govde, uzun pil omru ve gunluk is akislari icin dengeli performans sunan laptop modeli.", 1L, 1L, 18, "https://images.unsplash.com/photo-1517336714739-489689fd1ca8?auto=format&fit=crop&w=1200&q=80", "Ince tasarim ve uzun pil omru.", true),
                new Product(102L, "Vertex Pro 16", new BigDecimal("45999"), "Yuksek islem gucu ve buyuk ekrani ile tasarim ile yazilim islerinde kullanima uygun premium laptop.", 1L, 4L, 9, "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=1200&q=80", "Yuksek performansli premium laptop.", true),
                new Product(103L, "QuantumEdge Work 15", new BigDecimal("38999"), "Yuksek hizli SSD ve 32 GB RAM kombinasyonu ile coklu gorev kullaniminda akici deneyim sunar.", 1L, 6L, 14, "https://images.unsplash.com/photo-1484788984921-03950022c9ef?auto=format&fit=crop&w=1200&q=80", "Coklu gorev odakli guclu laptop.", false),
                new Product(201L, "PulseOne X7", new BigDecimal("24999"), "120 Hz ekran, hizli sarj ve gelismis kamera sistemi ile amiral gemisi telefon deneyimi sunar.", 2L, 2L, 26, "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=1200&q=80", "Kamera odakli amiral gemisi telefon.", true),
                new Product(202L, "Arclight S10", new BigDecimal("18999"), "Dengeli fiyat performans segmentinde AMOLED ekran ve guvenli biyometrik kilit ozellikleri sunar.", 2L, 3L, 34, "https://images.unsplash.com/photo-1522273400909-fd1a8f77637e?auto=format&fit=crop&w=1200&q=80", "Dengeli ve hizli telefon.", false),
                new Product(203L, "Lumina Neo 5G", new BigDecimal("21999"), "Parlak ekran, uzun pil omru ve gece cekimlerinde net sonuclar veren kamera paketi sunar.", 2L, 8L, 21, "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?auto=format&fit=crop&w=1200&q=80", "Parlak ekranli 5G telefon.", true),
                new Product(301L, "NovaSound Max", new BigDecimal("5499"), "Aktif gurultu engelleme ve uzun pil omru ile toplanti ve muzik kullanimina uygun kulaklik.", 3L, 1L, 42, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=1200&q=80", "ANC destekli kablosuz kulaklik.", true),
                new Product(302L, "Vertex Beat Mini", new BigDecimal("3499"), "Kompakt tasarim ve suya dayanikli yapi ile acik hava kullanimina uygun bluetooth hoparlor.", 3L, 4L, 47, "https://images.unsplash.com/photo-1545454675-3531b543be5d?auto=format&fit=crop&w=1200&q=80", "Kompakt ve guclu hoparlor.", false),
                new Product(303L, "SkyGrid Studio Pods", new BigDecimal("4699"), "Dusuk gecikme, cift cihaz baglantisi ve net mikrofon performansi ile gunluk kullanimda konfor saglar.", 3L, 10L, 53, "https://images.unsplash.com/photo-1606220588913-b3aacb4d2f46?auto=format&fit=crop&w=1200&q=80", "Cift baglanti destekli kulaklik.", true),
                new Product(401L, "Vertex Dock 8-in-1", new BigDecimal("2299"), "USB-C, HDMI ve Ethernet gibi baglantilari tek govdede toplayan cok amacli docking istasyonu.", 4L, 4L, 51, "https://images.unsplash.com/photo-1580906855284-4a0b75bb9f8a?auto=format&fit=crop&w=1200&q=80", "Hepsi bir arada dock cozumu.", false),
                new Product(402L, "Arclight FastCharge 65W", new BigDecimal("1199"), "GaN tabanli kompakt adaptor, telefon ve laptoplarda hizli sarj destegi saglar.", 4L, 3L, 74, "https://images.unsplash.com/photo-1583863788434-e58a36330cf0?auto=format&fit=crop&w=1200&q=80", "Kompakt hizli sarj adaptoru.", false),
                new Product(403L, "CoreSphere Flex Stand", new BigDecimal("899"), "Ayarlanabilir aci ve katlanabilir yapi ile tablet ve telefon kullaniminda ergonomi saglar.", 4L, 9L, 65, "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=1200&q=80", "Katlanabilir masaustu stand.", false),
                new Product(501L, "HelioWare Tab 11", new BigDecimal("17499"), "Kalem destegi ve genis ekrani ile not alma, cizim ve medya tuketiminde akici deneyim sunar.", 5L, 5L, 23, "https://images.unsplash.com/photo-1561154464-82e9adf32764?auto=format&fit=crop&w=1200&q=80", "Kalem destekli cok yonlu tablet.", true),
                new Product(502L, "NovaSlate Plus", new BigDecimal("20999"), "120 Hz ekran ve guclu islemci ile yaratici uygulamalar icin dusuk gecikmeli kullanim sunar.", 5L, 1L, 17, "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?auto=format&fit=crop&w=1200&q=80", "Yuksek tazeleme hizli tablet.", true),
                new Product(503L, "IronPeak Pad Go", new BigDecimal("13999"), "Hafif govde ve uzun pil omru sayesinde mobil calisma ve uzaktan egitim icin ideal bir modeldir.", 5L, 7L, 31, "https://images.unsplash.com/photo-1585792180666-f7347c490ee2?auto=format&fit=crop&w=1200&q=80", "Tasinabilir ve uygun fiyatli tablet.", false),
                new Product(601L, "PulseOne Strike RGB Keyboard", new BigDecimal("2799"), "Mekanik anahtar yapisi ve ozellestirilebilir RGB profilleri ile rekabetci oyunlar icin tasarlanmistir.", 6L, 2L, 44, "https://images.unsplash.com/photo-1618384887929-16ec33fab9ef?auto=format&fit=crop&w=1200&q=80", "Mekanik oyuncu klavyesi.", false),
                new Product(602L, "SkyGrid Falcon Mouse", new BigDecimal("1899"), "Yuksek DPI sensor, hafif govde ve programlanabilir tuslar ile hizli tepki verir.", 6L, 10L, 59, "https://images.unsplash.com/photo-1527814050087-3793815479db?auto=format&fit=crop&w=1200&q=80", "Hafif ve hizli oyuncu mouse.", true),
                new Product(603L, "QuantumEdge Aero Controller", new BigDecimal("2399"), "Dusuk gecikmeli kablosuz baglanti ve ergonomik tutus ile konsol ve PC uyumlu gamepad deneyimi sunar.", 6L, 6L, 36, "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?auto=format&fit=crop&w=1200&q=80", "PC ve konsol uyumlu gamepad.", false),
                new Product(701L, "Lumina Fit Watch 2", new BigDecimal("6799"), "Nabiz, uyku ve egzersiz takibi yapan hafif tasarimli akilli saat modeli.", 7L, 8L, 33, "https://images.unsplash.com/photo-1579586337278-3f436f25d4d4?auto=format&fit=crop&w=1200&q=80", "Spor odakli akilli saat.", true),
                new Product(702L, "CoreSphere Time Pro", new BigDecimal("8999"), "Ekran parlakligi yuksek, bildirim yonetimi guclu ve coklu spor modu sunan saat modeli.", 7L, 9L, 24, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=1200&q=80", "Gelisemis saglik takip ozellikleri.", false),
                new Product(703L, "HelioWare Active S", new BigDecimal("7499"), "Suya dayanikli govde ve haftalik pil omru ile aktif yasam tarzina uygun akilli saat.", 7L, 5L, 29, "https://images.unsplash.com/photo-1511385348-a52b4a160dc2?auto=format&fit=crop&w=1200&q=80", "Uzun pil omurlu akilli saat.", false),
                new Product(801L, "IronPeak Vision 27", new BigDecimal("12499"), "2K coznurluk ve 165 Hz tazeleme hizi ile hem oyun hem is kullanimina uygun monitor.", 8L, 7L, 19, "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=1200&q=80", "2K 165 Hz oyuncu monitoru.", true),
                new Product(802L, "SkyGrid ColorPro 32", new BigDecimal("16999"), "Genis renk gamutu ve USB-C baglanti destegi ile icerik ureticileri icin optimize edilmistir.", 8L, 10L, 11, "https://images.unsplash.com/photo-1522199755839-a2bacb67c546?auto=format&fit=crop&w=1200&q=80", "Renk dogru profesyonel monitor.", true),
                new Product(803L, "NovaView Office 24", new BigDecimal("7999"), "Dusuk mavi isik modu ve ayarlanabilir stand ile ofis kullaniminda konfor odakli ekran sunar.", 8L, 1L, 38, "https://images.unsplash.com/photo-1545239351-1141bd82e8a6?auto=format&fit=crop&w=1200&q=80", "Ofis odakli ergonomik monitor.", false),
                new Product(901L, "Arclight Cam 4K", new BigDecimal("15499"), "Optik sabitleme ve 4K kayit ozelligi ile vlog ve urun cekimleri icin ideal kameradir.", 9L, 3L, 13, "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=1200&q=80", "4K kayit yapabilen kompakt kamera.", true),
                new Product(902L, "HelioWare Drone Mini", new BigDecimal("22999"), "Katlanabilir govde ve otomatik takip modu ile kolay kullanim sunan baslangic seviyesi drone.", 9L, 5L, 8, "https://images.unsplash.com/photo-1473968512647-3e447244af8f?auto=format&fit=crop&w=1200&q=80", "Takip modlu kompakt drone.", false),
                new Product(903L, "CoreSphere Action One", new BigDecimal("11999"), "Sarsinti engelleme ve su gecirmez govde ile hareketli cekimlerde net goruntu sunar.", 9L, 9L, 22, "https://images.unsplash.com/photo-1502982720700-bfff97f2ecac?auto=format&fit=crop&w=1200&q=80", "Suya dayanikli aksiyon kamerasi.", false),
                new Product(1001L, "QuantumEdge Mesh Router X", new BigDecimal("6999"), "Cok odali evlerde stabil kapsama alani sunan mesh destekli yeni nesil router.", 10L, 6L, 27, "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?auto=format&fit=crop&w=1200&q=80", "Genis kapsama alanli mesh router.", true),
                new Product(1002L, "IronPeak NAS Home 2-Bay", new BigDecimal("13499"), "Ev ve kucuk ofis icin merkezi yedekleme ve uzaktan erisim imkani sunan NAS cihazi.", 10L, 7L, 16, "https://images.unsplash.com/photo-1587202372775-e229f172b9d7?auto=format&fit=crop&w=1200&q=80", "Ev kullanimi icin 2 yuvali NAS.", false),
                new Product(1003L, "SkyGrid Portable SSD 2TB", new BigDecimal("4999"), "Yuksek okuma yazma hizi ve dayanikli govde ile buyuk dosyalari hizli tasiyabilen harici SSD.", 10L, 10L, 48, "https://images.unsplash.com/photo-1597872200969-2b65d56bd16b?auto=format&fit=crop&w=1200&q=80", "Hizli ve dayanikli harici SSD.", true)
            );
            productRepository.saveAll(products);
            log.info("Products seeded successfully. Total products: {}", products.size());
        }
    }
}
