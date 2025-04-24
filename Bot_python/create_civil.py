import requests
import time
import random
from faker import Faker
from datetime import datetime, timedelta

fake = Faker("tr_TR")

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

    return {
        "personName": person_name,
        "personLastName": last_name,
        "gender": gender,
        "birthDate": birth_date
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
