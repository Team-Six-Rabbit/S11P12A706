import BoardHeader from "@/components/common/BoardHeader"; // BoardHeader 컴포넌트 임포트

interface CategoryContentProps {
	categories: { id: string; name: string }[];
	selectedCategory: string;
	handleCategorySelect: (category: string) => void;
}

function CategoryContent({
	categories,
	selectedCategory,
	handleCategorySelect,
}: CategoryContentProps) {
	return (
		<div className="p-10 pt-20">
			<BoardHeader
				boardName="불구경"
				categories={categories}
				selectedCategory={selectedCategory}
				onCategorySelect={handleCategorySelect}
			/>
			<div className="mt-4">
				{categories.map((category) => (
					<div
						key={category.id}
						id={category.id}
						className={`p-4 rounded bg-gray-100 ${selectedCategory === category.name ? "block" : "hidden"}`}
					>
						# {category.name}
					</div>
				))}
			</div>
		</div>
	);
}

export default CategoryContent;
