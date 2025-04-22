import os
import sys
from collections import defaultdict
from datetime import datetime, timedelta

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
PARENT_DIR = os.path.abspath(os.path.join(CURRENT_DIR, '..'))  # 프로젝트 루트
sys.path.append(PARENT_DIR)

from utils.generate_weeks import (START_DATE, format_period,
                                  update_or_create_readme)

WEEKDAYS_KR = ['월', '화', '수', '목', '금', '토', '일']
TASKS_MARKER_START = '<!-- 요일별 기록 시작 -->'
TASKS_MARKER_END = '<!-- 요일별 기록 끝 -->'
BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))


def parse_filename(filename: str):
    try:
        base = os.path.splitext(filename)[0]
        parts = base.split('_')
        if len(parts) < 3:
            return None
        site = parts[0]
        date_str = parts[-1]
        title = '_'.join(parts[1:-1])
        date = datetime.strptime(date_str, '%y%m%d')
        return date, site, title
    except:
        return None


def collect_tasks_by_weekday(directory: str):
    task_map = defaultdict(list)
    for fname in os.listdir(directory):
        fpath = os.path.join(directory, fname)
        if not os.path.isfile(fpath):
            continue
        parsed = parse_filename(fname)
        if parsed is None:
            continue
        date, site, title = parsed
        weekday_kr = WEEKDAYS_KR[date.weekday()]
        site_name = {
            'boj': '백준',
            'prog': '프로그래머스',
            'codeforces': '코드포스'
        }.get(site.lower(), site.capitalize())
        task_map[weekday_kr].append(f"{site_name} {title}")
    return task_map

def format_daily_tasks_block(tasks: dict) -> str:
    lines = [TASKS_MARKER_START, "## ✅ 요일별 문제 기록"]
    for day in WEEKDAYS_KR:
        if day in tasks:
            joined = ', '.join(tasks[day])
            lines.append(f"- {day}요일: {joined}")
    lines.append(TASKS_MARKER_END)
    return '\n'.join(lines)

def insert_or_update_section(content: str, new_block: str, marker_start: str, marker_end: str) -> str:
    if marker_start in content and marker_end in content:
        pre = content.split(marker_start)[0]
        post = content.split(marker_end)[-1]
        return f"{pre}{new_block}{post}"
    else:
        return content.strip() + '\n\n' + new_block

def update_readme_with_tasks(week_dir: str):
    task_map = collect_tasks_by_weekday(week_dir)
    if not task_map:
        return  # 기록할 것이 없으면 건너뜀

    new_block = format_daily_tasks_block(task_map)
    readme_path = os.path.join(week_dir, 'README.md')

    # ✅ README가 없으면 generate_weeks의 생성 로직 호출
    if not os.path.exists(readme_path):
        week_name = os.path.basename(week_dir)  # 예: 'week03'
        week_num = int(week_name.replace("week", ""))
        
        from generate_weeks import START_DATE
        week_start = START_DATE + timedelta(days=(week_num - 1) * 7)
        week_end = week_start + timedelta(days=6)
        period_block = format_period(week_start, week_end)

        update_or_create_readme(readme_path, period_block, week_num)

    # 📌 이후 기존 방식대로 업데이트
    with open(readme_path, 'r', encoding='utf-8-sig') as f:
        content = f.read()

    updated = insert_or_update_section(content, new_block, TASKS_MARKER_START, TASKS_MARKER_END)

    with open(readme_path, 'w', encoding='utf-8-sig') as f:
        f.write(updated)


def main():
    for i in range(1, 27):  # week01 ~ week26
        week_folder = os.path.join(BASE_DIR, f'week{str(i).zfill(2)}')
        if os.path.isdir(week_folder):
            update_readme_with_tasks(week_folder)

if __name__ == '__main__':
    main()
