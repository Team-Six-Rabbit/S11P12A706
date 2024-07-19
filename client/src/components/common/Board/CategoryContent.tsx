import { useState } from "react";
import BoardHeader from "./BoardHeader";
import Card from "./Card";

interface Category {
	id: string;
	name: string;
}

interface CategoryContentProps {
	categories: Category[];
	selectedCategory: string;
	handleCategorySelect: (category: string) => void;
}

function CategoryContent({
	categories,
	selectedCategory,
	handleCategorySelect,
}: CategoryContentProps) {
	const [selectedStatus, setSelectedStatus] = useState<string>("live");

	const handleStatusSelect = (status: string) => {
		setSelectedStatus(status);
	};

	// Sample data for the cards with unique IDs
	const sampleCards = [
		{
			id: "1",
			image: "https://picsum.photos/400/400",
			title: "A 주장 VS B 주장",
			description: "대결자 A",
			status: "라이브",
			viewers: 32,
		},
		{
			id: "2",
			image: "https://picsum.photos/400/400",
			title: "A 주장 VS B 주장",
			description: "대결자 A",
			status: "라이브",
			viewers: 32,
		},
		{
			id: "3",
			image: "https://picsum.photos/400/400",
			title: "A 주장 VS B 주장",
			description: "대결자 A",
			status: "라이브",
			viewers: 32,
		},
		{
			id: "4",
			image: "https://picsum.photos/400/400",
			title: "A 주장 VS B 주장",
			description: "대결자 A",
			status: "라이브",
			viewers: 32,
		},
	];

	return (
		<div className="p-10 pt-20">
			<BoardHeader
				boardName="불구경"
				categories={categories}
				selectedCategory={selectedCategory}
				onCategorySelect={handleCategorySelect}
				selectedStatus={selectedStatus}
				onStatusSelect={handleStatusSelect}
			/>
			{/* <div className="mt-4">
				<div className="flex justify-between items-center mt-4">
					<h2 className="text-xl">현재 선택된 카테고리: #{selectedCategory}</h2>
					<RadioButtonGroup
						selectedStatus={selectedStatus}
						onStatusSelect={handleStatusSelect}
					/>
				</div>
			</div> */}
			<div className="mt-4 grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-6">
				{sampleCards.map((card) => (
					<Card
						key={card.id}
						image={card.image}
						title={card.title}
						description={card.description}
						status={card.status}
						viewers={card.viewers}
					/>
				))}
			</div>
			<div className="mt-4 grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-6">
				{sampleCards.map((card) => (
					<Card
						key={card.id}
						image={card.image}
						title={card.title}
						description={card.description}
						status={card.status}
						viewers={card.viewers}
					/>
				))}
			</div>
		</div>
	);
}

export default CategoryContent;
