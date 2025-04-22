import base64
import io
import os
import re
from collections import defaultdict

import matplotlib.pyplot as plt
import numpy as np
import requests

# 상위 디렉토리 경로 설정
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
README_PATH = os.path.join(BASE_DIR, 'README.md')
WEEK_PREFIX = 'week'
SOLVED_AC_API = 'https://solved.ac/api/v3/problem/show?problemId='
MARKER_START = '<!-- 백준 통계 시작 -->'
MARKER_END = '<!-- 백준 통계 끝 -->'
CHART_MARKER_START = '<!-- 차트 시작 -->'
CHART_MARKER_END = '<!-- 차트 끝 -->'

def get_problem_info(problem_id: str):
    try:
        resp = requests.get(SOLVED_AC_API + problem_id)
        if resp.status_code == 200:
            return resp.json()
    except Exception as e:
        print(f"문제 {problem_id} 조회 실패: {e}")
    return None

def parse_file(filename: str):
    # 예: 240401_boj_1000.py
    match = re.match(r'\d{6}_(\w+)_([\w\d]+)', filename)
    if not match:
        return None, None
    site, problem_id = match.group(1).lower(), match.group(2)
    return site, problem_id

def collect_statistics():
    total = 0
    boj_total = 0
    other_total = 0
    boj_tags = defaultdict(int)
    weekly_counts = defaultdict(int)
    
    for folder in os.listdir(BASE_DIR):
        if not folder.startswith(WEEK_PREFIX):
            continue
        folder_path = os.path.join(BASE_DIR, folder)
        if not os.path.isdir(folder_path):
            continue
            
        week_number = folder.replace(WEEK_PREFIX, '')
        
        for fname in os.listdir(folder_path):
            fpath = os.path.join(folder_path, fname)
            if not os.path.isfile(fpath):
                continue
            site, problem_id = parse_file(fname)
            if not site:
                continue
                
            total += 1
            weekly_counts[week_number] += 1
            
            if site == 'boj':
                boj_total += 1
                info = get_problem_info(problem_id)
                if info and 'tags' in info:
                    for tag in info['tags']:
                        name = next((t['name'] for t in tag['displayNames'] if t['language'] == 'ko'), tag['key'])
                        boj_tags[name] += 1
            else:
                other_total += 1
                
    return total, boj_total, other_total, boj_tags, weekly_counts

def create_pie_chart(boj_total, other_total):
    labels = ['백준', '기타 사이트']
    sizes = [boj_total, other_total]
    colors = ['#4285F4', '#34A853']
    
    plt.figure(figsize=(8, 6))
    plt.pie(sizes, labels=labels, colors=colors, autopct='%1.1f%%', startangle=90)
    plt.axis('equal')
    plt.title('사이트별 문제 풀이 비율')
    
    img = io.BytesIO()
    plt.savefig(img, format='png', bbox_inches='tight')
    plt.close()
    img.seek(0)
    
    return base64.b64encode(img.getvalue()).decode()

def create_bar_chart(boj_tags):
    # 상위 10개 태그만 표시
    top_tags = dict(sorted(boj_tags.items(), key=lambda x: x[1], reverse=True)[:10])
    
    plt.figure(figsize=(10, 6))
    bars = plt.bar(top_tags.keys(), top_tags.values(), color='#4285F4')
    plt.xticks(rotation=45, ha='right')
    plt.title('백준 문제 유형별 풀이 수 (상위 10개)')
    plt.ylabel('문제 수')
    plt.tight_layout()
    
    # 막대 위에 값 표시
    for bar in bars:
        height = bar.get_height()
        plt.annotate(f'{height}',
                    xy=(bar.get_x() + bar.get_width() / 2, height),
                    xytext=(0, 3),
                    textcoords="offset points",
                    ha='center', va='bottom')
    
    img = io.BytesIO()
    plt.savefig(img, format='png', bbox_inches='tight')
    plt.close()
    img.seek(0)
    
    return base64.b64encode(img.getvalue()).decode()

def create_weekly_progress_chart(weekly_counts):
    # 주차별 문제 풀이 추이 그래프
    weeks = sorted(weekly_counts.keys(), key=int)
    counts = [weekly_counts[week] for week in weeks]
    
    plt.figure(figsize=(12, 6))
    plt.plot(weeks, counts, marker='o', linestyle='-', color='#4285F4', linewidth=2)
    plt.title('주차별 문제 풀이 수')
    plt.xlabel('주차')
    plt.ylabel('문제 수')
    plt.grid(True, linestyle='--', alpha=0.7)
    
    # 각 포인트에 값 표시
    for i, count in enumerate(counts):
        plt.annotate(f'{count}', 
                    xy=(weeks[i], counts[i]),
                    xytext=(0, 5),
                    textcoords="offset points",
                    ha='center')
    
    plt.xticks(weeks)
    plt.tight_layout()
    
    img = io.BytesIO()
    plt.savefig(img, format='png', bbox_inches='tight')
    plt.close()
    img.seek(0)
    
    return base64.b64encode(img.getvalue()).decode()

def format_stats(total, boj_total, other_total, boj_tags, weekly_counts):
    lines = [MARKER_START]
    lines.append("## 📊 문제 풀이 통계")
    lines.append(f"- 전체 문제 수: {total}문제")
    lines.append(f"- 백준 문제 수: {boj_total}문제")
    lines.append(f"- 기타 사이트 문제 수: {other_total}문제")
    
    # 차트 삽입
    lines.append("\n" + CHART_MARKER_START)
    
    # 파이 차트 (사이트별 비율)
    pie_chart_base64 = create_pie_chart(boj_total, other_total)
    lines.append(f"\n### 사이트별 문제 풀이 비율\n")
    lines.append(f"<img src=\"data:image/png;base64,{pie_chart_base64}\" alt=\"사이트별 문제 풀이 비율\" width=\"600\"/>\n")
    
    # 주차별 진행 차트
    weekly_chart_base64 = create_weekly_progress_chart(weekly_counts)
    lines.append(f"\n### 주차별 문제 풀이 추이\n")
    lines.append(f"<img src=\"data:image/png;base64,{weekly_chart_base64}\" alt=\"주차별 문제 풀이 추이\" width=\"800\"/>\n")
    
    if boj_total > 0:
        # 태그별 바 차트
        bar_chart_base64 = create_bar_chart(boj_tags)
        lines.append(f"\n### 백준 문제 유형별 풀이 수 (상위 10개)\n")
        lines.append(f"<img src=\"data:image/png;base64,{bar_chart_base64}\" alt=\"백준 문제 유형별 풀이 수\" width=\"800\"/>\n")
        
        lines.append("\n### 📘 백준 문제 유형별 풀이 수")
        for tag, count in sorted(boj_tags.items(), key=lambda x: -x[1]):
            percent = round(count / boj_total * 100, 1)
            lines.append(f"- {tag}: {count}문제 ({percent}%)")
    
    lines.append(CHART_MARKER_END)
    lines.append(MARKER_END)
    return '\n'.join(lines)

def update_readme(content_block: str):
    if os.path.exists(README_PATH):
        with open(README_PATH, 'r', encoding='utf-8') as f:
            content = f.read()
    else:
        content = ''
    
    if MARKER_START in content and MARKER_END in content:
        pre = content.split(MARKER_START)[0]
        post = content.split(MARKER_END)[-1]
        new_content = f"{pre}{content_block}{post}"
    else:
        new_content = content.strip() + "\n\n" + content_block
    
    with open(README_PATH, 'w', encoding='utf-8') as f:
        f.write(new_content)

def main():
    total, boj_total, other_total, boj_tags, weekly_counts = collect_statistics()
    stats_block = format_stats(total, boj_total, other_total, boj_tags, weekly_counts)
    update_readme(stats_block)
    print(f"README.md 파일이 {README_PATH}에 성공적으로 업데이트되었습니다.")

if __name__ == '__main__':
    main()