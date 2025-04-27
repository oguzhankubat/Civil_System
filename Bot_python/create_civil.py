import requests
import time
import random
from faker import Faker
from datetime import datetime, timedelta

fake = Faker("tr_TR")

ilceler = {
    "Adana": ["Seyhan", "Yüreğir", "Çukurova", "Kozan", "İmamoğlu", "Aladağ"],
    "Adıyaman": ["Merkez", "Besni", "Kahta", "Gölbaşı", "Samsat"],
    "Afyonkarahisar": ["Merkez", "Sandıklı", "Dinar", "Emirdağ", "İhsaniye"],
    "Ağrı": ["Merkez", "Patnos", "Doğubeyazıt", "Eleşkirt", "Taşlıçay"],
    "Amasya": ["Merkez", "Suluova", "Merzifon", "Gümüşhacıköy", "Hamamözü"],
    "Ankara": ["Çankaya", "Keçiören", "Mamak", "Yenimahalle", "Sincan", "Altındağ", "Pursaklar"],
    "Antalya": ["Muratpaşa", "Kepez", "Konyaaltı", "Alanya", "Serik", "Kemer"],
    "Artvin": ["Merkez", "Hopa", "Ardanuç", "Şavşat", "Borçka"],
    "Aydın": ["Efeler", "Kuşadası", "Didim", "Nazilli", "Söke", "Bozdoğan"],
    "Balıkesir": ["Altıeylül", "Karesi", "Bandırma", "Edremit", "Ayvalık", "Burhaniye"],
    "Bilecik": ["Merkez", "İnhisar", "Bozüyük", "Gölpazarı", "Osmaneli"],
    "Bingöl": ["Merkez", "Solhan", "Genç", "Karlıova", "Yedisu"],
    "Bitlis": ["Merkez", "Tatvan", "Ahlat", "Güroymak", "Hizan"],
    "Bolu": ["Merkez", "Gerede", "Mudurnu", "Dörtdivan", "Seben"],
    "Burdur": ["Merkez", "Ağlasun", "Bucak", "Çavdır", "Karamanlı"],
    "Bursa": ["Osmangazi", "Nilüfer", "Yıldırım", "Gemlik", "Mudanya", "İnegöl"],
    "Çanakkale": ["Merkez", "Ayvacık", "Ezine", "Gökçeada", "Lapseki"],
    "Çorum": ["Merkez", "Alaca", "İskilip", "Osmancık", "Sungurlu"],
    "Denizli": ["Merkezefendi", "Pamukkale", "Çivril", "Buldan", "Tavas"],
    "Diyarbakır": ["Merkez", "Bağlar", "Kayapınar", "Sur", "Yenişehir"],
    "Edirne": ["Merkez", "Keşan", "Enez", "Uzunköprü", "Lalapaşa"],
    "Elazığ": ["Merkez", "Keban", "Karakoçan", "Maden", "Palu"],
    "Erzincan": ["Merkez", "Erzincan Merkez", "Kemaliye", "Refahiye", "Üzümlü"],
    "Erzurum": ["Merkez", "Aziziye", "Palandöken", "Yakutiye", "Oltu"],
    "Eskişehir": ["Tepebaşı", "Odunpazarı", "Sarıcakaya", "Mahmudiye", "Alpu"],
    "Gaziantep": ["Şahinbey", "Şehitkamil", "Oğuzeli", "Nizip", "Karkamış"],
    "Giresun": ["Merkez", "Eynesil", "Şebinkarahisar", "Alucra", "Piraziz"],
    "Gümüşhane": ["Merkez", "Kelkit", "Şiran", "Torul", "Köse"],
    "Hakkâri": ["Merkez", "Yüksekova", "Şemdinli", "Çukurca", "Derecik"],
    "Hatay": ["Antakya", "İskenderun", "Defne", "Reyhanlı", "Altınözü"],
    "Isparta": ["Merkez", "Eğirdir", "Aksu", "Senirkent", "Keçiborlu"],
    "İstanbul": ["Kadıköy", "Üsküdar", "Bakırköy", "Beşiktaş", "Fatih", "Kartal", "Beylikdüzü", "Bağcılar"],
    "İzmir": ["Konak", "Karşıyaka", "Bornova", "Buca", "Bayraklı", "Menemen"],
    "Kahramanmaraş": ["Merkez", "Dulkadiroğlu", "Onikişubat", "Afşin", "Elbistan"],
    "Karabük": ["Merkez", "Safranbolu", "Eflani", "Ovacık", "Yenice"],
    "Kırıkkale": ["Merkez", "Bahşili", "Delice", "Karakeçili", "Keskin"],
    "Kırklareli": ["Merkez", "Lüleburgaz", "Vize", "Pınarhisar", "Babaeski"],
    "Kırşehir": ["Merkez", "Akpınar", "Mucur", "Kaman", "Çiçekdağı"],
    "Kocaeli": ["Izmit", "Gebze", "Derince", "Kartepe", "Çayırova", "Dilovası"],
    "Konya": ["Meram", "Selçuklu", "Karatay", "Ereğli", "Akşehir"],
    "Kütahya": ["Merkez", "Tavşanlı", "Simav", "Gediz", "Hisarcık"],
    "Malatya": ["Merkez", "Battalgazi", "Yeşilyurt", "Akçadağ", "Pütürge"],
    "Manisa": ["Şehzadeler", "Yunusemre", "Akhisar", "Salihli", "Turgutlu"],
    "Mardin": ["Merkez", "Artuklu", "Kızıltepe", "Nusaybin", "Derik"],
    "Mersin": ["Akdeniz", "Toroslar", "Mezitli", "Yenişehir", "Erdemli"],
    "Muğla": ["Menteşe", "Fethiye", "Bodrum", "Marmaris", "Dalaman"],
    "Muş": ["Merkez", "Varto", "Bulanık", "Malazgirt", "Korkut"],
    "Nevşehir": ["Merkez", "Avanos", "Gülşehir", "Hacıbektaş", "Derinkuyu"],
    "Niğde": ["Merkez", "Bor", "Ulukışla", "Altunhisar", "Çiftlik"],
    "Ordu": ["Altınordu", "Fatsa", "Ünye", "Perşembe", "Gölköy"],
    "Osmaniye": ["Merkez", "Düziçi", "Kadirli", "Toprakkale", "Sumbas"],
    "Rize": ["Merkez", "Pazar", "Ardeşen", "Fındıklı", "Çayeli"],
    "Sakarya": ["Adapazarı", "Serdivan", "Erenler", "Arifiye", "Hendek"],
    "Samsun": ["Atakum", "Canik", "İlkadım", "Bafra", "Vezirköprü"],
    "Siirt": ["Merkez", "Pervari", "Şirvan", "Tillo", "Eruh"],
    "Sinop": ["Merkez", "Ayancık", "Boyabat", "Durağan", "Erfelek"],
    "Sivas": ["Merkez", "Zara", "Divriği", "Kangal", "Şarkışla"],
    "Tekirdağ": ["Merkez", "Süleymanpaşa", "Çerkezköy", "Kapaklı", "Marmara Ereğlisi"],
    "Tokat": ["Merkez", "Erbaa", "Niksar", "Zile", "Reşadiye"],
    "Trabzon": ["Merkez", "Akçaabat", "Ortahisar", "Araklı", "Sürmene"],
    "Tunceli": ["Merkez", "Pertek", "Hozat", "Mazgirt", "Pülümür"],
    "Şanlıurfa": ["Merkez", "Haliliye", "Eyyübiye", "Karaköprü", "Birecik"],
    "Uşak": ["Merkez", "Banaz", "Eşme", "Ulubey", "Sivaslı"],
    "Van": ["Merkez", "İpekyolu", "Edremit", "Özalp", "Gevaş"],
    "Yalova": ["Merkez", "Çınarcık", "Altınova", "Armutlu", "Termal"],
    "Yozgat": ["Merkez", "Saraykent", "Şefaatli", "Aydıncık", "Boğazlıyan"],
    "Zonguldak": ["Merkez", "Çaycuma", "Ereğli", "Alaplı", "Devrek"],
}
mahalleler = [
    "Yıldız", "Çamlıca", "Gültepe", "Saray", "Ataköy", "İncirli", "Kocatepe", "Bahçelievler",
    "Mevlana", "İstiklal", "Cumhuriyet", "Barbaros", "Yeşiltepe", "Huzur", "Yeni Mahalle",
    "Muratpaşa", "Zeytinburnu", "Karadeniz", "Güzeltepe", "Kuzeytepe", "Şehitkamil", "Serdivan",
    "Merkezefendi", "Fatih", "Süleymanpaşa", "Erenler", "Laleli", "Kocatepe", "Sefaköy",
    "Tepebaşı", "Narlıdere", "Çınar", "Görükle", "Küçükçekmece", "Avcılar", "Işıklar", "Kadıköy",
    "Tunalı Hilmi", "Osmangazi", "Sultanbeyli", "Beylikdüzü", "Altınova", "Yedinci Tepe",
    "Büyükçekmece", "Vatan", "Ömerli", "Emlak Konut", "Meydan", "Çakmak", "Çiftlik", "Gökçe",
    "Beyazıt", "Gülyüz", "İnciraltı", "Pelitli", "Zambak", "Sümbül", "Kocatepe", "Çıkrıkçı",
    "Dumlupınar", "Aydınlık", "Küçükçekmece", "Beşiktas", "Mavişehir", "Güzelbahçe",
    "Kırkpınar", "Mescit", "Huzur", "Uludağ", "Mehmet Akif", "İlkbahar", "Vatan", "Gökçeada",
    "Yenimahalle", "Adalet", "Cevizli", "Yalı", "Dikmen", "Atatürk", "Evren", "Köroğlu",
    "Özgür", "Çayırova", "Beykoz", "Gazi", "Kalkınma", "Zeytinlik", "Sazlıdere", "Ahi Evran",
    "Süleymanpaşa", "İnönü", "Karaköy", "Davutpaşa", "Beşevler", "Mehmet Akif Ersoy", "Büyükşehir",
    "Gölbaşı", "Yenikent", "Kurtuluş", "Yılmaz", "Aşağı Mahalle", "Yukarı Mahalle", "Büyük Mahalle",
    "Bozkır", "Çiftlikköy", "Fethiye", "Altınşehir", "Beylikdüzü", "Seyran", "Yalova", "Özbek", "Muş"
]

def create_address():
    il = random.choice(list(ilceler.keys()))
    ilce = random.choice(ilceler[il])
    sokak = fake.street_name()
    mahalle = random.choice(mahalleler)
    apartman_no = random.randint(1, 100)

    return f"{mahalle} Mah. {sokak} Sok. No: {apartman_no} {ilce}/{il}"

def create_birth_place():
    return random.choice(list(ilceler.keys()))

def create_birth_date():
    baslangic_tarihi = datetime(1950, 1, 1)
    bitis_tarihi = datetime(2005, 12, 31)
    fark = bitis_tarihi - baslangic_tarihi
    rastgele_gun = random.randrange(fark.days)
    rastgele_tarih = baslangic_tarihi + timedelta(days=rastgele_gun)
    return rastgele_tarih.strftime("%Y-%m-%d")


def create_civil():
    last_name = fake.last_name()
    gender = random.choice(["m", "f"])
    person_name = fake.first_name_male() if gender == "m" else fake.first_name_female()
    birth_date = create_birth_date()
    address = create_address()
    birth_place=create_birth_place()

    return {
        "personName": person_name,
        "personLastName": last_name,
        "gender": gender,
        "birthDate": birth_date,
        "residenceAdress": address,
        "birthPlace":birth_place
    }


def send_data_to_api(data):
    url = "http://localhost:8086/api/person/add"

    headers = {
        "Content-Type": "application/json",
    }

    try:
        response = requests.post(url, json=data, headers=headers)

        response_data = response.json()
        print("Başarılı Gönderim:", response_data)

    except requests.exceptions.HTTPError as http_err:
        print("HTTP Hatası:", response.status_code, "-", response.text)
    except Exception as err:
        print("Bilinmeyen Bir Hata Oluştu:", err)


def main():
    while True:
        data = create_civil()
        print("Gönderilecek veri:", data)
        send_data_to_api(data)
        time.sleep(.002)


if __name__ == "__main__":
    main()
