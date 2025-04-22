import os
from datetime import datetime, timedelta

# === 설정 ===
START_DATE = datetime(2025, 4, 1)  # Week01 시작일 (화요일)
NUM_WEEKS = 26  # 생성할 주차 수
TARGET_DIR = os.path.dirname(__file__)  # 현재 스크립트 위치 기준

# 한글 요일 매핑
WEEKDAYS_KR = ['월', '화', '수', '목', '금', '토', '일']

# 마커 구간 이름
MARKER_START = '<!-- 기간 시작 -->'
MARKER_END = '<!-- 기간 끝 -->'

def format_period(start: datetime, end: datetime):
    start_str = f"{start.month}월 {start.day}일 ({WEEKDAYS_KR[start.weekday()]})"
    end_str = f"{end.month}월 {end.day}일 ({WEEKDAYS_KR[end.weekday()]})"
    return f"{MARKER_START}\n📆 기간: {start_str} ~ {end_str}\n{MARKER_END}"


def generate_readme_content(week_num: int, period_block: str):
    return f"# 📘 Week {str(week_num).zfill(2)}\n\n{period_block}\n"


def update_or_create_readme(path: str, new_period_block: str, week_num: int):
    if os.path.exists(path):
        # 이미 README.md가 존재하면 아무것도 하지 않음
        return

    with open(path, 'w', encoding='utf-8-sig', errors='replace') as f:
        f.write(generate_readme_content(week_num, new_period_block))


    with open(path, 'r', encoding='utf-8-sig', errors='replace') as f:
        content = f.read()

    if MARKER_START in content and MARKER_END in content:
        pre = content.split(MARKER_START)[0]
        post = content.split(MARKER_END)[-1]
        updated = f"{pre}{new_period_block}{post}"
    else:
        # 마커가 없으면 맨 위에 자동 삽입
        updated = generate_readme_content(week_num, new_period_block) + '\\n' + content

    with open(path, 'w', encoding='utf-8-sig', errors='replace') as f:
        f.write(updated)


def main():
    for i in range(NUM_WEEKS):
        week_num = i + 1
        week_dir = os.path.join(TARGET_DIR, f"../week{str(week_num).zfill(2)}")
        os.makedirs(week_dir, exist_ok=True)

        week_start = START_DATE + timedelta(days=i * 7)
        week_end = week_start + timedelta(days=6)

        period_block = format_period(week_start, week_end)
        readme_path = os.path.join(week_dir, "README.md")

        update_or_create_readme(readme_path, period_block, week_num)

if __name__ == '__main__':
    main()