interface CardProps {
	image: string;
	title: string;
	description: string;
	status: string;
	viewers: number;
}

function Card({ image, title, description, status, viewers }: CardProps) {
	return (
		<div className="relative flex flex-col border-solid border-black border-4 shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm">
			<div className="absolute top-2 left-2 bg-red-600 text-white px-2 py-1 rounded z-10">
				{status}
			</div>
			<div className="h-auto overflow-hidden">
				<div className="h-44 overflow-hidden relative">
					<img src={image} alt={title} className="w-full h-full object-cover" />
				</div>
			</div>
			<div className="bg-white py-4 px-3 border-solid border-t-4">
				<h3 className="text-xl mb-2 font-medium">{title}</h3>
				<div className="flex justify-between items-center">
					<p className="text-base text-black">{description}</p>
					<div className="text-sm text-black">{viewers}명 시청중</div>
				</div>
			</div>
		</div>
	);
}

export default Card;
