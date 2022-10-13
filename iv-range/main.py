import math


def main():
    price = float(input('price: '))
    iv = float(input('implied volatility: '))
    timeframes = {
        'daily': 256,
        'weekly': 52,
        'monthly': 12,
        '30 day': 8.53,
        '45 day': 5.69,
        '60 day': 4.27,
        'quarterly': 4,
        'by-annually': 2,
        'annually': 1
    }
    for v, k in timeframes.items():
        diff = per_diff(iv, k)
        p_diff = price * (diff/100)
        print()
        print(f'{v} iv {p_diff:.2f}%')
        print(f'{(price - p_diff):.2f} to {(price + p_diff):.2f}')


def per_diff(iv, per):
    return iv/math.sqrt(per)


if __name__ == '__main__':
    main()
