import { useState } from "react";
import Header from "@/components/header";
import CategoryContent from "@/components/common/Board/CategoryContent"; // CategoryContent 컴포넌트 임포트

const categories = [
	{ id: "all", name: "전체" },
	{ id: "love", name: "사랑" },
	{ id: "daily", name: "일상" },
	{ id: "food", name: "음식" },
	{ id: "entertainment", name: "연예" },
	{ id: "games", name: "게임" },
	{ id: "sports", name: "스포츠" },
	{ id: "others", name: "기타" },
];

function LiveBoardPage() {
	const [selectedCategory, setSelectedCategory] = useState<string>("전체");

	const handleCategorySelect = (category: string) => {
		setSelectedCategory(category);
	};

	return (
		<div>
			<Header />
			<CategoryContent
				categories={categories}
				selectedCategory={selectedCategory}
				handleCategorySelect={handleCategorySelect}
			/>
		</div>
	);
}

export default LiveBoardPage;
